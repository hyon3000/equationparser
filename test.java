package test;

public class test {
	public static void main(String[] args) {
		try {
			program p=new program();
			System.out.println(p.compileandsolve("++++a"));
			System.out.println(p.compileandsolve("(a<2?b:c)=1"));
			System.out.println(p.compileandsolve("b"));
			System.out.println(p.compileandsolve("c"));
			System.out.println(p.compileandsolve("d=2+3i;f=4+5i;g=d**f"));
			System.out.println(p.compileandsolve("max(a,4,1)"));
			System.out.println(p.compileandsolve("true^^false"));
			equation e=program.compile("a++;a+1");
			program p2=new program(p);//copy program p
			System.out.println(p2.solve(e));
			program p3=new program(p);//copy program p
			System.out.println(p3.solve(e));
		}catch(Exception E) {
			System.out.print(E.toString());
		}
	}
}