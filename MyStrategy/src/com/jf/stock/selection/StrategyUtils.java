package com.jf.stock.selection;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jf.stock.model.Dayline2;
import com.jf.stock.model.Macd;
import com.jf.stock.utils.Constant;
import com.jf.stock.utils.TradeDayUtils;
import com.jf.stock.utils.Utils;

public class StrategyUtils {
	public static boolean isGreaterThen120(Dayline2 dayLine, Session session) {
		session.getTransaction().begin();

		Query q1 = session
				.createSQLQuery("select avg(a.close) from (select close from dayline2 where obj = ? order by date desc limit 0,120) a; ");
		q1.setString(0, dayLine.getId().getObj());

		Double avgClose = Utils.getDouble(q1.uniqueResult());
		session.getTransaction().commit();

		if (dayLine.getClose() < avgClose) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean isWeekLineStandOnYearLine(Dayline2 dayLine,
			Session session) throws ParseException {
		session.getTransaction().begin();
		try{
		Query query = session
				.createQuery("from Dayline2 where id.obj = ? order by id.date asc");
		query.setString(0, dayLine.getId().getObj());
		List result = query.list();
		
		List<KLine> weekLine = getCycleKLine(result, Constant.WEEK_I);
		List<KLine> yearLine = getCycleKLine(result, Constant.YEAR_I);
		if (weekLine.size() < 2 || yearLine.size() < 2) {
			return false;
		}

		KLine todayWeekLine = weekLine.get(weekLine.size() - 1);
		KLine t_1WeekLine = weekLine.get(weekLine.size() - 2);

		KLine todayYearLine = weekLine.get(yearLine.size() - 1);
		KLine t_1YearLine = weekLine.get(yearLine.size() - 2);

		if (t_1WeekLine.getClose() < t_1YearLine.getClose()
				&& todayWeekLine.getClose() >= todayYearLine.getClose()) {
			// 量比
			double lb = (todayWeekLine.getVolume() - todayYearLine.getVolume())
					/ todayYearLine.getVolume();
			if (lb >= 0.5) {
				return true;
			}
		}
		session.getTransaction().commit();
		}
		catch(Exception e)
		{
			session.getTransaction().rollback();
		}
		return false;
	}

	public static boolean isMAWeekLineStandOnYearLine(Dayline2 dayLine,
			Session session) throws ParseException {
		MALine today5 = getMaLine(dayLine, session, dayLine.getId().getDate(),
				5);
		MALine yestoday5 = getMaLine(
				dayLine,
				session,
				Integer.valueOf(TradeDayUtils.getPreTradeDay(dayLine.getId()
						.getDate() + "")), 5);

		MALine today250 = getMaLine(dayLine, session,
				dayLine.getId().getDate(), 250);
		MALine yestoday250 = getMaLine(
				dayLine,
				session,
				Integer.valueOf(TradeDayUtils.getPreTradeDay(dayLine.getId()
						.getDate() + "")), 250);

		if (yestoday5.getPrice() < yestoday250.getPrice()
				&& today5.getPrice() >= today250.getPrice()) {
			if ((today5.getVolume() - today250.getVolume())
					/ today250.getVolume() >= 0.5) {
				return true;
			}
		}

		return false;
	}

	public static boolean isMACDChangeToRed(Dayline2 dayLine, Session session) {
		try {
			session.getTransaction().begin();
		
			Query q2 = session
					.createQuery("from Macd where id.obj = ? order by id.date desc");
			q2.setString(0, dayLine.getId().getObj());
			q2.setFirstResult(0);
			q2.setMaxResults(2);

			List l2 = q2.list();

			if (l2 == null || l2.size() == 0) {
				return false;
			}

			double macdT = (((Macd) l2.get(0)).getDif() - ((Macd) l2.get(0))
					.getDea()) * 2;
			double macdT_1 = (((Macd) l2.get(1)).getDif() - ((Macd) l2.get(1))
					.getDea()) * 2;

			session.getTransaction().commit();
			if (macdT > 0 && macdT_1 < 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		}
	}

	public static boolean isBottomDivergence(Dayline2 dayLine, Session session)
			throws Exception {

		Query q2 = session
				.createQuery("from Macd where id.obj = ? and id.date <= ? order by id.date desc");
		q2.setString(0, dayLine.getId().getObj());
		q2.setFirstResult(0);
		q2.setInteger(1, dayLine.getId().getDate());
		q2.setMaxResults(5);

		List l2 = q2.list();

		if (l2 == null || l2.size() < 5) {
			return false;
		}

		double difT = ((Macd) l2.get(0)).getDif();
		double difT_1 = ((Macd) l2.get(1)).getDif();
		double macdT = (((Macd) l2.get(0)).getDif() - ((Macd) l2.get(0))
				.getDea()) * 2;
		double macdT_1 = (((Macd) l2.get(1)).getDif() - ((Macd) l2.get(1))
				.getDea()) * 2;
		double macdT_2 = (((Macd) l2.get(2)).getDif() - ((Macd) l2.get(2))
				.getDea()) * 2;
		double macdT_3 = (((Macd) l2.get(3)).getDif() - ((Macd) l2.get(3))
				.getDea()) * 2;
		double macdT_4 = (((Macd) l2.get(4)).getDif() - ((Macd) l2.get(4))
				.getDea()) * 2;

		if (macdT_4 >= macdT_3 && macdT_3 >= macdT_2 && macdT_2 >= macdT_1
				&& macdT_4 < 0 && macdT >= macdT_1) {
				if (difT > difT_1) {
					return true;
				}
		}

		return false;
	}

	public static List<KLine> getCycleKLine(List dayLineList, int field)
			throws ParseException {
		List<KLine> list = new ArrayList<KLine>();
		for (int i = 0; i < dayLineList.size(); i++) {
			Dayline2 dayLine = (Dayline2) dayLineList.get(i);

			if (list.size() == 0) {
				KLine kLine = new KLine();
				kLine.setDate(dayLine.getId().getDate());
				kLine.setAmount(dayLine.getAmout());
				kLine.setClose(dayLine.getClose());
				kLine.setHigh(dayLine.getHigh());
				kLine.setLow(dayLine.getLow());
				kLine.setOpen(dayLine.getOpen());
				kLine.setVolume(dayLine.getVol());
				list.add(kLine);
			} else {
				KLine kLine = list.get(list.size() - 1);
				if (Utils.isSameTimeRange(kLine.getDate(), dayLine.getId()
						.getDate(), field)) {
					kLine.setHigh(Math.max(kLine.getHigh(), dayLine.getHigh()));
					kLine.setClose(dayLine.getClose());
					kLine.setDate(dayLine.getId().getDate());
					kLine.setAmount(kLine.getAmount() + dayLine.getAmout());
					kLine.setLow(Math.min(kLine.getLow(), dayLine.getLow()));
					kLine.setVolume(dayLine.getVol() + kLine.getVolume());
				} else {
					KLine _kLine = new KLine();
					_kLine.setDate(dayLine.getId().getDate());
					_kLine.setAmount(dayLine.getAmout());
					_kLine.setClose(dayLine.getClose());
					_kLine.setHigh(dayLine.getHigh());
					_kLine.setLow(dayLine.getLow());
					_kLine.setOpen(dayLine.getOpen());
					_kLine.setVolume(dayLine.getVol());
					list.add(_kLine);
				}
			}
		}

		return list;
	}

	public static MALine getMaLine(Dayline2 dayLine, Session session, int day,
			int days) {
		session.getTransaction().begin();
		Query query = session
				.createSQLQuery("select avg(a.close),avg(a.vol) from ( select close,vol from dayline2 where obj = ? and date <= ? order by date desc limit 0,"
						+ days + ") a");
		query.setString(0, dayLine.getId().getObj());
		query.setInteger(1, dayLine.getId().getDate());

		Object[] object = (Object[]) query.uniqueResult();
		MALine ma = new MALine();

		ma.setPrice((Double) object[0]);
		ma.setVolume((Double) object[1]);
		session.getTransaction().commit();
		return ma;
	}
	
	public static int getMinVolume(Dayline2 dayLine, Session session,
			int days) {
		String sql = "select b.date from"
				+" (select vol,date from dayline2 where obj = '"+dayLine.getId().getObj()+"' order by date desc limit 0,90) b" 
				+" where b.vol =" 
				+" ("
				+" select min(a.vol) from" 
				+" ("
				+" select vol from dayline2 where obj = '"+dayLine.getId().getObj()+"' and date <= "+dayLine.getId().getDate()+" order by date desc limit 0,"+days+") a"
				+" ) ";
		Query query = session.createSQLQuery(sql);
		List result = query.list();

		return (Integer)result.get(0);
	}

	public static boolean ZDFGreater(Dayline2 dayline, double a) {
		double zdf = (dayline.getClose() - dayline.getLclose())
				/ dayline.getLclose();
		if (zdf > a) {
			return true;
		}
		return false;
	}
}
