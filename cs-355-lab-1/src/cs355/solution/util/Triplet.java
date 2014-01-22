package cs355.solution.util;

public class Triplet<T1, T2, T3>
{
	public T1	fst;
	public T2	snd;
	public T3	thd;

	public Triplet()
	{}

	public Triplet(T1 fst, T2 snd, T3 thd)
	{
		this.fst = fst;
		this.snd = snd;
		this.thd = thd;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.fst == null) ? 0 : this.fst.hashCode());
		result = prime * result + ((this.snd == null) ? 0 : this.snd.hashCode());
		result = prime * result + ((this.thd == null) ? 0 : this.thd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Triplet))
			return false;
		Triplet<?, ?, ?> other = (Triplet<?, ?, ?>) obj;

		if (!this.getClass().getTypeParameters().equals(other.getClass().getTypeParameters()))
			return false;

		if (this.fst == null)
		{
			if (other.fst != null)
				return false;
		}
		else if (!this.fst.equals(other.fst))
			return false;
		if (this.snd == null)
		{
			if (other.snd != null)
				return false;
		}
		else if (!this.snd.equals(other.snd))
			return false;
		if (this.thd == null)
		{
			if (other.thd != null)
				return false;
		}
		else if (!this.thd.equals(other.thd))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Triplet (");
		builder.append(fst.getClass().getSimpleName());
		builder.append("=[");
		builder.append(this.fst);
		builder.append("], ");
		builder.append(snd.getClass().getSimpleName());
		builder.append("=[");
		builder.append(this.snd);
		builder.append("], ");
		builder.append(thd.getClass().getSimpleName());
		builder.append("=[");
		builder.append(this.thd);
		builder.append("])");
		return builder.toString();
	}
}