package pzhao.com;

public class PermStr {
	public static final int SWITCH = 1; // 是否去除重复的开关, 1表示去重, 0表示不去重, 默认为1

	private static boolean need_swap(StringBuilder str, int start, int i)

	{

		if (SWITCH == 0)

			return true;

		else

		{

			for (int j = start; j <= i - 1; j++)

			{

				if (str.charAt(j) == str.charAt(i))

					return false;

			}

			return true;

		}

	}

	public static void permStr(StringBuilder str, int start, int end)

	{

		if (start == end - 1)

			System.out.println(str.toString());

		for (int i = start; i <= end - 1; i++)

		{

			if (need_swap(str, start, i))

			{

				swap(str, start, i);

				permStr(str, start + 1, end);

				swap(str, start, i);

			}

		}

	}

	private static void swap(StringBuilder str, int p, int q)

	{

		char temp = str.charAt(q);

		str.setCharAt(q, str.charAt(p));

		str.setCharAt(p, temp);

	}

	public static void main(String[] args)

	{

		StringBuilder str = new StringBuilder();

		str.append("abcab");

		permStr(str, 0, str.length());

	}

}
