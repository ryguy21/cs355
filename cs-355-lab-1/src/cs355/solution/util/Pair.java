package cs355.solution.util;

public class Pair<T1, T2>
{
	public T1	fst;
	public T2	snd;

	public Pair()
	{}

	public Pair(T1 fst, T2 snd)
	{
		this.fst = fst;
		this.snd = snd;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.fst == null) ? 0 : this.fst.hashCode());
		result = prime * result + ((this.snd == null) ? 0 : this.snd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pair))
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;

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
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Pair (");
		builder.append(fst.getClass().getSimpleName());
		builder.append("=[");
		builder.append(fst);
		builder.append("], ");
		builder.append(snd.getClass().getSimpleName());
		builder.append("=[");
		builder.append(snd);
		builder.append("])");
		return builder.toString();
	}
}