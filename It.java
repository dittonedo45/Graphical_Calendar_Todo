import java.util.*;
import java.time.*;
import java.lang.*;


public class
It {
	public
	static
	void
	main (String[] args)
	{
		Date date;

		var it=new All (2023);

		while ((date=it.next ())!=null)
		{
			System.out.println (date);
		}
	}
}

class All implements Iterator<Date>
{
	private int year;
	private int cur_month;
	private Days it;

	public All (int year)
	{
		this.year=year;
		this.cur_month=0;
		it=new Days (year, cur_month);
	}
	public Date next()
	{
		Date date=it.next_date ();

		if (date==null)
		{
			it=new Days (year, cur_month+1);
			cur_month++;
		}

		return date;
	}

	public boolean hasNext ()
	{
		if ((it.hasNext ()==false)
			&& (cur_month>=12)) // Remember the month ends at 11 since 11 is 12.
		{
			return false;
		}
		return true;
	}
};

class Days implements Iterator<Date>
{
	private Date date;
	private int year;
	private int month;

	private boolean first_time;

	public Date next_date ()
	{
		if (hasNext ())
		{
			return next();
		}
		return null;
	}

	public Days (int year, int month)
	{
		this.year=year;
		this.month=month;
		date=new Date(year, month, 1);
		first_time=true;
	}
	public Date next ()
	{
		Calendar cal=Calendar.getInstance ();
		cal.setTime (date);

		if (first_time)
		{
			first_time=false;
			return date;
		}else{
			cal.add(Calendar.DAY_OF_MONTH,1);
			date=cal.getTime ();

			return date;
		}
	}
	public boolean hasNext ()
	{
		Calendar cal=Calendar.getInstance ();
		cal.setTime (date);
		cal.add(Calendar.DAY_OF_MONTH,1);

		if (first_time)
		{
			return true;
		}

		date=cal.getTime ();
		boolean ret=date.getYear() ==year
			&& date.getMonth()==month;

		cal.add(Calendar.DAY_OF_MONTH,-1);
		date=cal.getTime ();

		return ret;
	}
	public void remove ()
	{
		Calendar cal=Calendar.getInstance ();
		cal.setTime (date);
		cal.add(Calendar.DAY_OF_MONTH,-1);
		date=cal.getTime ();

		return;
	}
}
