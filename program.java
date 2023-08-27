package test;

import java.util.ArrayList;
class complex{
    double r,i;
    boolean isbool;
    complex(complex a) {r=a.r;i=a.i;isbool=a.isbool;}
    complex() {r=0;i=0;isbool=false;}
    complex(double rr,double ii) {r=rr;i=ii;isbool=false;}
    complex(boolean p) { i=0; r=(p?1:0); isbool=true;}
    complex(String s) {
        if(s.equals("i")) {r=0;i=1;}
        else{
            int tmp=s.indexOf("i");
            if(tmp!=-1) { r=0; i=Double.parseDouble(s.substring(0,tmp));}
            else { r=Double.parseDouble(s); i=0;}
        }
        isbool=false;
    }
    String tostr(double res) {
        if(Double.isInfinite(r) || Double.isNaN(r)||Double.isInfinite(r) || Double.isNaN(r)) return "NaN";
        String ret="";
        if(isbool) {if(r!=0) return "true"; else return "false";}
        complex c=new complex(r,i);
        double rr=(r>0?r:-r);double ii=(i>0?i:-i);
        double res2=res*1e-2*(rr>ii?rr:ii);
        if(-res2<r&&r<res2) c.r=0;
        if(-res2<i&&i<res2) c.i=0;
        if(c.r!=0) ret+=Double.toString(c.r);
        if(c.i==1) {if(c.r!=0) ret+="+i"; else ret="i";}
        else if(c.i==-1) ret+="-i";
        else if(c.i<0) ret+= c.i +"i";
        else if(c.i>0) {
            if(c.r!=0) ret+="+"+ c.i +"i";
            else ret+= c.i +"i";
        }
        if(ret.equals("")) ret=Double.toString(0);
        return ret;
    }
    boolean istrue() {return r>1e-6||r<-1e-6||i>1e-6||i<-1e-6;}
    boolean isequal(double res,complex a) {
        double tr=(r>0?r:-r);double ti=(i>0?i:-i);
        double ar=(a.r>0?a.r:-a.r);double ai=(a.i>0?a.i:-a.i);
        double delta=((ar>ai?ar:ai)+(tr>ti?tr:ti))*1e-12+res;
        double realdelta=(r>a.r?r-a.r:a.r-r),imagdelta=(i>a.i?i-a.i:a.i-i);
        return realdelta < delta && imagdelta < delta;
    }
    boolean islarge(complex a) {
        double tr=(r>0?r:-r)*1e-12;double ti=(1e-8<tr?tr:1e-8);
        double ar=(a.r>0?a.r:-a.r)*1e-12;double ai=(1e-8<tr?tr:1e-8);
        if(i<-ti||ti<i||a.i<-ai||ai<a.i) return false;
        else return r > a.r + tr + ar;
    }
    boolean islargeorequal(double res,complex a) {
        double tr=(r>0?r:-r);double ti=(i>0?i:-i);
        double ar=(a.r>0?a.r:-a.r);double ai=(a.i>0?a.i:-a.i);
        double delta=((ar>ai?ar:ai)+(tr>ti?tr:ti))*1e-12+res;
        double realdelta=(r>a.r?r-a.r:a.r-r),imagdelta=(i>a.i?i-a.i:a.i-i);
        if(realdelta<delta&&imagdelta<delta) return true;
        else {
            tr*=1e-12;ti=(1e-8<tr?tr:1e-8);
            ar*=1e-12;ai=(1e-8<tr?tr:1e-8);
            if(i<-ti||ti<i||a.i<-ai||ai<a.i) return false;
            else return r > a.r + tr + ar;
        }
    }
    boolean isreal() {return i<1e-6&&i>-1e-6;}
    void store_add(complex a,complex b) {r=a.r+b.r;i=a.i+b.i;}
    void store_add(complex a) {r+=a.r;i+=a.i;}
    void store_add(complex a,double b) {r=a.r+b;i=a.i;}
    void store_sub(complex a,complex b) {r=a.r-b.r;i=a.i-b.i;}
    void store_sub(complex b) {r= 1. -b.r;i=-b.i;	}
    void store_mul(complex a,complex b) {r=a.r*b.r-a.i*b.i;i=a.i*b.r+a.r*b.i;}
    void store_mul(complex a,double b) {r=a.r*b;i=a.i*b;}
    void store_div(complex a,complex b) {double t=b.r*b.r+b.i*b.i;r=(a.r*b.r+a.i*b.i)/t;i=(a.i*b.r-a.r*b.i)/t;}
    void store_div(double a,complex b) {double t=b.r*b.r+b.i*b.i;r=(a*b.r)/t;i=(-a*b.i)/t;}
    void store_mod(complex a,complex b) {
        if(a.isreal()&&b.isreal()) {r=a.r-Math.floor(a.r/b.r)*b.r;i=0;}
        else {
            double t=b.r*b.r+b.i*b.i;
            double rt=Math.round((a.r*b.r+a.i*b.i)/t);
            double it=Math.round((a.i*b.r-a.r*b.i)/t);
            r=a.r-rt*b.r+it*b.i; i=a.i-it*b.r-rt*b.i;
        }
    }
    void store_pow(complex a,complex b) {
        double arg;
        if(a.i==0&&a.r<0) arg=Math.PI; else arg=Math.atan2(a.i,a.r);
        double a2b2c2=Math.pow(a.r*a.r+a.i*a.i,b.r/2);
        double edarg=Math.pow(Math.E, -b.i*arg);
        double hdloga2b2=0.5*b.i*Math.log(a.r*a.r+a.i*a.i)+b.r*arg;
        r=a2b2c2*edarg*Math.cos(hdloga2b2);
        i=a2b2c2*edarg*Math.sin(hdloga2b2);
    }
    complex cbrt() {
        double arg;
        if(i==0&&r<0) arg=Math.PI; else arg=Math.atan2(i,r);
        double a2b2c2=Math.pow(r*r+i*i, 0.3333333333333333 /2); double carg= 0.3333333333333333 *arg;
        return new complex(a2b2c2*Math.cos(carg),a2b2c2*Math.sin(carg));
    }
    complex sqrt() {
        double arg;
        if(i==0&&r<0) arg=Math.PI; else arg=Math.atan2(i,r);
        double a2b2c2=Math.pow(r*r+i*i,0.25); double carg=0.5*arg;
        return new complex(a2b2c2*Math.cos(carg),a2b2c2*Math.sin(carg));
    }
    complex ln() {
        double arg;
        if(i==0&&r<0) arg=Math.PI; else arg=Math.atan2(i,r);
        return new complex(0.5*Math.log(r*r+i*i),arg);
    }
    void store_log(complex a,complex b) {store_div(b.ln(),a.ln());}
    complex round() {return new complex(Math.rint(r),Math.rint(i));}
    complex sin() {return new complex(Math.sin(r)*Math.cosh(i),Math.cos(r)*Math.sinh(i));}
    complex cos() {return new complex(Math.cos(r)*Math.cosh(i),-Math.sin(r)*Math.sinh(i));}
    complex tan() {complex tmp=new complex(0,0);tmp.store_div(sin(), cos());return tmp;}
    complex sec() {complex tmp=new complex(0,0);tmp.store_div(1,cos());return tmp;}
    complex csc() {complex tmp=new complex(0,0);tmp.store_div(1, sin());return tmp;}
    complex cot() {complex tmp=new complex(0,0);tmp.store_div(cos(), sin());return tmp;}
    complex asin() {//
        complex tmp=new complex(0,0);tmp.store_mul(this, this);//x^2
        complex tmp2=new complex(0,0);tmp2.store_sub(tmp);//1-x^2
        complex tmp3=new complex(0,0);tmp3.store_mul(new complex(0,1),this);//ix
        complex tmp4=new complex(0,0);tmp4.store_add(tmp2.sqrt(), tmp3);
        complex tmp5=new complex(0,0);tmp5.store_mul(new complex(0,-1),tmp4.ln());
        return tmp5;
    }
    complex acos() {//
        complex tmp=new complex(0,0); tmp.store_mul(this, this);//x^2
        complex tmp2=new complex(0,0); tmp2.store_sub(tmp);//1-x^2
        complex tmp3=new complex(0,0); tmp3.store_mul(new complex(0,1),tmp2.sqrt());//isqrt(1-x^2)
        complex tmp4=new complex(0,0); tmp4.store_add(tmp3,this);
        complex tmp5=new complex(0,0); tmp5.store_mul(new complex(0,-1),tmp4.ln());
        return tmp5;
    }
    complex atan() {
        complex I=new complex(0,1);//i
        complex tmp=new complex(0,0); tmp.store_sub(I,this);
        complex tmp2=new complex(0,0); tmp2.store_add(I, this);
        complex tmp3=new complex(0,0); tmp3.store_div(tmp, tmp2);
        complex tmp4=new complex(0,0); tmp4.store_mul(new complex(0,-0.5),tmp3.ln());
        return tmp4;
    }
    complex sinh() {return new complex(Math.sinh(r)*Math.cos(i),Math.cosh(r)*Math.sin(i));}
    complex cosh() {return new complex(Math.cosh(r)*Math.cos(i),Math.sinh(r)*Math.sin(i));}
    complex tanh() {complex tmp=new complex(0,0);tmp.store_div(sinh(), cosh());return tmp;}
    complex sech() {complex tmp=new complex(0,0);tmp.store_div(1, cosh());return tmp;}
    complex csch() {complex tmp=new complex(0,0);tmp.store_div(1, sinh());return tmp;}
    complex coth() {complex tmp=new complex(0,0);tmp.store_div(cosh(), sinh());return tmp;}
    complex asinh() {//
        complex tmp=new complex(0,0);tmp.store_mul(this, this);//x^2
        complex tmp2=new complex(0,0);tmp2.store_add(tmp,1);//1+x^2
        complex tmp4=new complex(0,0);tmp4.store_add(tmp2.sqrt(), this);
        return tmp4.ln();
    }
    complex acosh() {
        complex tmp=new complex(0,0);tmp.store_mul(this, this);//x^2
        complex tmp2=new complex(0,0);tmp2.store_add(tmp,-1);//1+x^2
        complex tmp4=new complex(0,0);tmp4.store_add(tmp2.sqrt(), this);
        return tmp4.ln();
    }//
    complex atanh() {
        complex tmp=new complex(0,0);tmp.store_add(this,1);
        complex tmp2=new complex(0,0);tmp2.store_sub(this);
        complex tmp3=new complex(0,0);tmp3.store_div(tmp, tmp2);
        complex tmp4=new complex(0,0);tmp4.store_mul(tmp3.ln(),.5);
        return tmp4;
    }
    complex exp() {double ep=Math.exp(r);return new complex(ep*Math.cos(i),ep*Math.sin(i));}
    complex gamma() {
        double pi=3.1415926535897932384626433;
        if(r<1) {
            complex sinpz=new complex(); sinpz.store_mul(this,pi);
            complex tmp1=new complex(); tmp1.store_mul(sinpz.sin(),new complex(1-r,-i).gamma());
            complex tmp=new complex(); tmp.store_div(pi, tmp1); return tmp;
        }
        else if(r>=13) {
            complex t=new complex();complex u=new complex(r-1,i);complex w=new complex(r-12,i);
            t.store_mul(u,new complex(r-2,i));u.store_mul(t,new complex(r-3,i));
            t.store_mul(u,new complex(r-4,i));u.store_mul(t,new complex(r-5,i));
            t.store_mul(u,new complex(r-6,i));u.store_mul(t,new complex(r-7,i));
            t.store_mul(u,new complex(r-8,i));u.store_mul(t,new complex(r-9,i));
            t.store_mul(u,new complex(r-10,i));u.store_mul(t,new complex(r-11,i));
            t.store_mul(u,w);u.store_mul(t,w.gamma());return u;
        }
        else if(r>=5) {
            complex t=new complex();complex u=new complex(r-1,i);complex w=new complex(r-4,i);
            t.store_mul(u,new complex(r-2,i));u.store_mul(t,new complex(r-3,i));
            t.store_mul(u,w);u.store_mul(t,w.gamma());return u;
        }
        else if(r>=2) {
            complex t=new complex();complex u=new complex(r-1,i);
            t.store_mul(u,u.gamma());return t;
        }
        else {
            complex x=new complex();
            complex x2=new complex();
            x2.store_div(4951.5280766184523147263916810986, new complex(r,i));
            x.store_add(x2,2.5066282746310003264504604057148);
            x2.store_div(-11022.603040137560339627548142594, new complex(r+1,i));x.store_add(x2);
            x2.store_div(8679.5333964151991293273081810147, new complex(r+2,i));x.store_add(x2);
            x2.store_div(-2900.1316731806645263485970828959, new complex(r+3,i));x.store_add(x2);
            x2.store_div(387.36969755881701873307862545538, new complex(r+4,i));x.store_add(x2);
            x2.store_div(-15.675630083821163344370742742881, new complex(r+5,i));x.store_add(x2);
            x2.store_div(0.08683652788036852727411312260578, new complex(r+6,i));x.store_add(x2);
            x2.store_div(-1.8743606693980897457853174363129e-6, new complex(r+7,i));x.store_add(x2);
            x2.store_div(1.5802098929804450748205087768261e-7, new complex(r+8,i));x.store_add(x2);
            x2.store_div(-6.8695945208181695458460348343656e-8, new complex(r+9,i));x.store_add(x2);
            x2.store_div(1.0148573065965312192431285724194e-8, new complex(r+10,i));x.store_add(x2);
            complex y=new complex();y.store_pow(new complex(r+7.5,i), new complex(r-0.5,i));
            complex y2=new complex();y2.store_mul(y, new complex(-r-7.5,-i).exp());
            y.store_mul(y2, x); return y;
        }
    }
    complex fact() {return new complex(r+1,i).gamma();}
}
class ints{
    static int gcd(int a, int b) {if (b == 0) return a; else return gcd(b, a % b);}
    static int lcm(int a, int b) {return (a * b) / gcd(a, b);}
}
class unit{
    complex c;String t;Integer o;
    ArrayList<complex> commalog=null;
    unit(complex c1,String t1,Integer o1) {c=c1;t=t1;o=o1;}
    unit(unit tmp) {
        if(tmp.c==null) c=null;else c=new complex(tmp.c);
        if(tmp.t==null) t=null;else t= tmp.t;
        if(tmp.o==null) o=null;else o=tmp.o;
    }
    String tostr() throws Exception {
        if(c!=null) return c.tostr(1e-9)+" ";
        else if(o!=null){
            if(o==-2) return "( "; else if(o==-1)return ") "; else if(o==1)return t+" "; else if(o==11)return "a++ ";
            else if(o==12)return "a-- "; else if(o==21)return "++a "; else if(o==22)return "--a ";
            else if(o==23)return "~ "; else if(o==24)return "! "; else if(o==25)return "-a "; else if(o==26)return "+a ";
            else if(o==31)return "** "; else if(o==41)return "* "; else if(o==42)return "/ "; else if(o==43)return "% ";
            else if(o==51)return "+ "; else if(o==52)return "- "; else if(o==61)return "<< "; else if(o==62)return ">> ";
            else if(o==71)return "< "; else if(o==72)return "> "; else if(o==81)return "== "; else if(o==82)return "!= ";
            else if(o==91)return "& "; else if(o==101)return "^ "; else if(o==111)return "| "; else if(o==121)return "&& ";
            else if(o==131)return "^^ "; else if(o==141)return "|| "; else if(o==151)return "? ";
            else if(o==152)return ": "; else if(o==153)return "?: "; else if(o==154)return "= ";
            else if(o==155)return "*= "; else if(o==156)return "/= "; else if(o==157)return "%= ";
            else if(o==158)return "+= "; else if(o==159)return "-= "; else if(o==160)return "<<= ";
            else if(o==161)return ">>= "; else if(o==162)return "&= "; else if(o==163)return "|= ";
            else if(o==164)return "^= "; else if(o==165)return "**= "; else if(o==171)return ", ";
            else if(o==181)return "; "; else throw new Exception("wrong equation-unknown token");
        }
        else return "#"+t+" ";
    }
}
class equation{
    ArrayList<unit> eq;
    public equation(ArrayList<unit> q) {eq=q;}
    @Override
    public String toString() {
        try {String ret="";int s=eq.size(); for(int i=0;i<s;i++) ret+=eq.get(i).tostr(); return ret;}
        catch(Exception E) { return E.toString();}
    }
}
public class program{
    ArrayList<String> vark=new ArrayList<>(); ArrayList<complex> varc=new ArrayList<>();
    ArrayList<unit> stack1=new ArrayList<>();
    double resol=1e-12;
    public program() {}
    public program(program pr) {
        int vks=pr.vark.size();
        for(int i=0;i<vks;i++) {vark.add(pr.vark.get(i));varc.add(new complex(pr.varc.get(i)));}
        resol=pr.resol;
    }
    public void setresolution(double v) {resol=v;}
    complex get(String name) throws Exception{
        if(name==null) throw new Exception("wrong equation");
        else if(name.equals("pi")) return new complex(3.14159265358979323846,0);
        else if(name.equals("e")) return new complex (2.71828182845904523536,0);
        else if(name.equals("NaN")) return new complex(Double.NaN,Double.NaN);
        else if(name.equals("true")) return new complex(true);
        else if(name.equals("false")) return new complex(false);
        else {
            complex tmp=null; int vks=vark.size();
            for(int i=0;i<vks;i++)
                if(vark.get(i).equals(name)) { tmp=varc.get(i);break;}
            if(tmp==null) {
                complex newc=new complex(0,0); vark.add(name);varc.add(newc); return newc;
            } else return tmp;
        }
    }
    public static equation compile(String in) throws Exception{//slow
        ArrayList<unit> temp=new ArrayList<>();
        while(in.length()!=0) {
            char tmp=in.charAt(0);
            if(tmp==' '||tmp=='\n'||tmp=='\t') in=in.substring(1);
            else if('0'<=tmp && tmp<='9' ||'a'<=tmp && tmp<='z' || 'A'<=tmp && tmp<='Z' ||tmp=='.') {
                int it=0; int number=-1;//-1:처음 0:일반숫자 1:(숫자)e->원래 오류이나 임시로 허용 2:변수 3:지수숫자
                boolean dot=false, imag=false;
                while(true) {
                    if(it<in.length()) {
                        char tmp2=in.charAt(it);
                        if('0'<=tmp2 && tmp2<='9') {
                            it++;
                            if(number==-1) number=0;
                            else if(number==1) number=3;
                        }
                        else if(tmp2=='.') {
                            it++;
                            if(number==-1) number=0;
                            else if(number==1) number=3;
                            if(dot) throw new Exception("wrong equation-wrong number format");
                            else dot=true;
                        }
                        else if(tmp2=='i') {
                            it++;
                            if(imag) number=2;
                            else imag=true;
                            if(number==-1) number=0;
                        }
                        else if(tmp2=='e'||tmp2=='E') {
                            if(number==3) throw new Exception("wrong equation-wrong number format");
                            else if(number==0) number=1;
                            else number=2;
                            it++;
                        }
                        else if('a'<=tmp2 && tmp2<='z'||'A'<=tmp2 && tmp2<='Z') {
                            if(number==3 || (number==0&& !imag)) throw new Exception("wrong equation-wrong variable name");
                            else number=2;
                            it++;
                        }
                        else if(tmp2=='+'||tmp2=='-') {//지수숫자를 위해 특별히 허용
                            if(number==1&&it>1&&(in.charAt(it-1)=='e'||in.charAt(it-1)=='E')) {
                                number=3;it++;
                            }
                            else break;
                        }
                        else break;
                    }
                    else break;
                }//number는 절대 -1일 수 없음
                String tmp2=in.substring(0,it);
                if(isfunction(tmp2)) temp.add(new unit(null,"$"+tmp2,1));
                else if(number==1) throw new Exception("wrong equation-wrong number format");
                else if(number==0||number==3) temp.add(new unit(new complex(tmp2),null,null));
                else {//number==2
                    char chk=tmp2.charAt(0);
                    if('0'<=chk && chk<='9') throw new Exception("wrong equation-wrong variable name");
                    else temp.add(new unit(null,tmp2,null));
                }
                in=in.substring(it);
            }
            else if(tmp=='+') {
                char t2mp=in.charAt(1);
                if(t2mp=='+') {
                    if(temp.size()!=0) {
                        unit t3mp=temp.get(temp.size()-1);
                        if(t3mp.c==null&&t3mp.o==null) temp.add(new unit(null,null,11));//후위
                        else temp.add(new unit(null,null,21));

                    }
                    else temp.add(new unit(null,null,21));
                    in=in.substring(2);
                }
                else if(t2mp=='=') {
                    temp.add(new unit(null,null,158));in=in.substring(2);
                }
                else if(temp.size()==0) {temp.add(new unit(null,null,26));in=in.substring(1);}
                else {
                    unit tmp2=temp.get(temp.size()-1);
                    if(tmp2.o==null||tmp2.o==11||tmp2.o==12||tmp2.o==-1) temp.add(new unit(null,null,51));
                    else temp.add(new unit(null,null,26));
                    in=in.substring(1);
                }
            }
            else if(tmp=='-') {
                char t2mp=in.charAt(1);
                if(t2mp=='-') {
                    if(temp.size()!=0) {
                        unit t3mp=temp.get(temp.size()-1);
                        if(t3mp.c==null&&t3mp.o==null) temp.add(new unit(null,null,12));//후위
                        else temp.add(new unit(null,null,22));
                    }
                    else temp.add(new unit(null,null,22));
                    in=in.substring(2);
                }
                else if(t2mp=='=') {temp.add(new unit(null,null,159));in=in.substring(2);}
                else if(temp.size()==0) {
                    temp.add(new unit(null,null,25));in=in.substring(1);
                }
                else {
                    unit tmp2=temp.get(temp.size()-1);
                    if(tmp2.o==null||tmp2.o==11||tmp2.o==12||tmp2.o==-1) temp.add(new unit(null,null,52));
                    else temp.add(new unit(null,null,25));
                    in=in.substring(1);
                }

            }
            else if(tmp=='!') {
                char tmp2=in.charAt(1);
                if(tmp2=='=') { temp.add(new unit(null,null,82));in=in.substring(2);}
                else { temp.add(new unit(null,null,24));in=in.substring(1);}
            }
            else if(tmp=='~') {temp.add(new unit(null,null,23));in=in.substring(1);}
            else if(tmp=='&') {
                char tmp2=in.charAt(1);
                if(tmp2=='&') { temp.add(new unit(null,null,121));in=in.substring(2);}
                else if(tmp2=='=') { temp.add(new unit(null,null,162));in=in.substring(2);}
                else { temp.add(new unit(null,null,91));in=in.substring(1);}
            }
            else if(tmp=='|') {
                char tmp2=in.charAt(1);
                if(tmp2=='|') { temp.add(new unit(null,null,141));in=in.substring(2);}
                else if(tmp2=='=') { temp.add(new unit(null,null,163));in=in.substring(2);}
                else { temp.add(new unit(null,null,111));in=in.substring(1);}
            }
            else if(tmp=='^') {
                char tmp2=in.charAt(1);
                if(tmp2=='^') { temp.add(new unit(null,null,131));in=in.substring(2);}
                else if(tmp2=='=') { temp.add(new unit(null,null,164));in=in.substring(2);}
                else { temp.add(new unit(null,null,101));in=in.substring(1);}
            }
            else if(tmp=='*') {
                char tmp2=in.charAt(1);
                if(tmp2=='*') {
                    char tmp3=in.charAt(2);
                    if(tmp3=='=') {temp.add(new unit(null,null,165));in=in.substring(3);}
                    else {temp.add(new unit(null,null,31));in=in.substring(2);}
                }
                else if(tmp2=='=') { temp.add(new unit(null,null,155));in=in.substring(2);}
                else { temp.add(new unit(null,null,41));in=in.substring(1);}
            }
            else if(tmp=='/') {
                char tmp2=in.charAt(1);
                if(tmp2=='=') { temp.add(new unit(null,null,156));in=in.substring(2);}
                else { temp.add(new unit(null,null,42));in=in.substring(1);}
            }
            else if(tmp=='%') {
                char tmp2=in.charAt(1);
                if(tmp2=='=') { temp.add(new unit(null,null,157));in=in.substring(2);}
                else { temp.add(new unit(null,null,43));in=in.substring(1);}
            }
            else if(tmp=='>') {
                char tmp2=in.charAt(1);
                if(tmp2=='>') {
                    char tmp3=in.charAt(2);
                    if(tmp3=='=') {temp.add(new unit(null,null,161));in=in.substring(3);}
                    else {temp.add(new unit(null,null,62));in=in.substring(2);}
                }
                else if(tmp2=='=') { temp.add(new unit(null,null,74));in=in.substring(2);}
                else { temp.add(new unit(null,null,72));in=in.substring(1);}
            }
            else if(tmp=='=') {
                char tmp2=in.charAt(1);
                if(tmp2=='=') { temp.add(new unit(null,null,81));in=in.substring(2);}
                else { temp.add(new unit(null,null,154));in=in.substring(1);}
            }
            else if(tmp=='<') {
                char tmp2=in.charAt(1);
                if(tmp2=='<') {
                    char tmp3=in.charAt(2);
                    if(tmp3=='=') {temp.add(new unit(null,null,160));in=in.substring(3);}
                    else {temp.add(new unit(null,null,61));in=in.substring(2);}
                }
                else if(tmp2=='=') { temp.add(new unit(null,null,73));in=in.substring(2);}
                else { temp.add(new unit(null,null,71));in=in.substring(1);}
            }
            else if(tmp=='(') {
                temp.add(new unit(null,null,-2));
                int sz=temp.size()-2;
                if(sz>-1) {
                    unit tm1=temp.get(sz);
                    if(tm1.o==null ||tm1.o==-1 || tm1.o==1 ||tm1.o==11||tm1.o==12) {
                        temp.add(new unit(new complex(Double.NaN,Double.NaN),null,null));temp.add(new unit(null,null,171));
                    }
                }
                in=in.substring(1);
            }
            else if(tmp==')') {
                int sz=temp.size(); Integer to=null; if(sz!=0) to=temp.get(sz-1).o;
                if(to!=null&&to==171)temp.set(sz-1,new unit(null,null,-1));
                else temp.add(new unit(null,null,-1));
                in=in.substring(1);
            }
            else if(tmp=='?') {temp.add(new unit(null,null,151));in=in.substring(1);}
            else if(tmp==':') {temp.add(new unit(null,null,152));in=in.substring(1);}
            else if(tmp==',') {temp.add(new unit(null,null,171));in=in.substring(1);}
            else if(tmp==';') {temp.add(new unit(null,null,181));in=in.substring(1);}
            else throw new Exception("wrong equation-unknown character");
        }
        ArrayList<unit> temp2=new ArrayList<>();
        ArrayList<unit> stack=new ArrayList<>();
        while(temp.size()!=0) {
            unit tmp=temp.get(0);temp.remove(0);
            if(tmp.o==null) temp2.add(tmp);
            else if(tmp.o==181) {//세미콜론
                while(stack.size()!=0) {
                    unit tmpu=stack.get(stack.size()-1);temp2.add(tmpu);stack.remove(stack.size()-1);
                }
                temp2.add(tmp);
            }
            else if(stack.size()==0 || tmp.o==-2) stack.add(tmp);//여는괄호 또는 처음 나온 기호
            else if(tmp.o==-1) {//닫는괄호
                unit tmp3;
                while(!((tmp3=stack.get(stack.size()-1)).o==-2)) {
                    temp2.add(tmp3);
                    if(stack.size()<=1) throw new Exception("wrong equation-parenthesis mismatch");
                    else stack.remove(stack.size()-1);
                }
                if(stack.size()==0) throw new Exception("wrong equation-parenthesis mismatch");
                else stack.remove(stack.size()-1);
            }
            else if(priority(stack.get(stack.size()-1))<priority(tmp)) stack.add(tmp);
            else {
                if(20<tmp.o && tmp.o<30 ||150<tmp.o && tmp.o<170) //오른쪽->왼쪽 연결성 연산자 ?:
                    while(stack.size()!=0 && priority(stack.get(stack.size()-1))>priority(tmp)) {
                        temp2.add(stack.get(stack.size()-1));stack.remove(stack.size()-1);
                    }
                else //왼쪽->오른쪽 연결성 연산자
                    while(stack.size()!=0 && priority(stack.get(stack.size()-1))>=priority(tmp)) {
                        temp2.add(stack.get(stack.size()-1));stack.remove(stack.size()-1);
                    }
                if(tmp.o==152) {
                    unit tmp0=stack.get(stack.size()-1);
                    if(150<tmp0.o&&tmp0.o<170) {//?:
                        while(stack.size() != 0 && 151 < tmp0.o) {//?제외
                            temp2.add(tmp0);stack.remove(stack.size()-1);tmp0=stack.get(stack.size()-1);
                        }
                        if(tmp0.o==151) stack.set(stack.size()-1,new unit(null,null,153));//?->?:
                        else throw new Exception("wrong equation-wrong ternary equation");
                    }
                }
                else stack.add(tmp);
            }
        }
        while(stack.size()!=0) {
            unit tmp=stack.get(stack.size()-1);temp2.add(tmp);stack.remove(stack.size()-1);
        }
        equation eq=new equation(temp2); valid(eq);
        return eq;
    }
    static int priority(unit d) throws Exception{
        if(d.o==1) return 0;
        else if(10<d.o&&d.o<20) return -1;
        else if(20<d.o&&d.o<30) return -2;
        else if(d.o==31) return -3;
        else if(40<d.o&&d.o<50) return -4;
        else if(50<d.o&&d.o<60) return -5;
        else if(60<d.o&&d.o<70) return -6;
        else if(70<d.o&&d.o<80) return -7;
        else if(80<d.o&&d.o<90) return -8;
        else if(d.o==91) return -9;
        else if(d.o==101) return -10;
        else if(d.o==111) return -11;
        else if(d.o==121) return -12;
        else if(d.o==131) return -13;
        else if(d.o==141) return -14;
        else if(150<d.o&&d.o<170) return -15;
        else if(d.o==171) return -17;
        else if(d.o==181) return -18;
        else if(d.o==-2) return -1000;//여는 괄호
        else throw new Exception("wrong equation-unknown error");
    }
    static boolean isfunction(String d) {
        return d.equals("pow")||d.equals("exp")||d.equals("sqrt")||d.equals("cbrt")||d.equals("ln")||
                d.equals("log")||d.equals("abs")||d.equals("arg")||d.equals("conj")||d.equals("real")||
                d.equals("imag")||d.equals("min")||d.equals("max")||d.equals("gcd")||d.equals("lcm")||
                d.equals("floor")||d.equals("ceil")||d.equals("round")||d.equals("fact")||d.equals("rand")||
                d.equals("isnan")||d.equals("isreal")||d.equals("sin")||d.equals("cos")||d.equals("tan")||
                d.equals("sec")||d.equals("csc")||d.equals("cot")||d.equals("asin")||d.equals("acos")||
                d.equals("atan")||d.equals("atan2")||d.equals("asec")||d.equals("acsc")||d.equals("acot")||
                d.equals("sinh")||d.equals("cosh")||d.equals("tanh")||d.equals("sech")||d.equals("csch")||
                d.equals("coth")||d.equals("asinh")||d.equals("acosh")||d.equals("atanh")||d.equals("asech")||
                d.equals("acsch")||d.equals("acoth");
    }
    static void valid(equation in) throws Exception{
        ArrayList<unit> stack1=new ArrayList<>();
        int eqs=in.eq.size();
        for(int i=0;i<eqs;i++) {
            unit tmp=in.eq.get(i);
            if(tmp.o==null) stack1.add(tmp);
            else {
                int stacksize;
                if(tmp.o==1) {//function call
                    if((stacksize=stack1.size())<1) throw new Exception("wrong equation-incomplete formula");
                    stack1.set(stacksize-1,new unit(new complex(0,0),null,null));
                }
                else if(tmp.o==11||tmp.o==12) {//a--
                    if((stacksize=stack1.size())<1) throw new Exception("wrong equation-incomplete formula");
                    unit v1=stack1.get(stacksize-1);
                    if(v1.t==null) throw new Exception("wrong equation-cannot modify rvalue");
                    stack1.set(stacksize-1,new unit(new complex(0,0),null,null));
                }
                else if(tmp.o==21||tmp.o==22) {//--a
                    if((stacksize=stack1.size())<1) throw new Exception("wrong equation-incomplete formula");
                    unit v1=stack1.get(stacksize-1);
                    if(v1.t==null) throw new Exception("wrong equation-cannot modify rvalue");
                }
                else if(tmp.o==23||tmp.o==24||tmp.o==25||tmp.o==26) {//!
                    if((stacksize=stack1.size())<1) throw new Exception("wrong equation-incomplete formula");
                    stack1.set(stacksize-1,new unit(new complex(0,0),null,null));
                }
                else if(30<tmp.o&&tmp.o<150) {//>>
                    if((stacksize=stack1.size())<2) throw new Exception("wrong equation-incomplete formula");
                    stack1.set(stacksize-2,new unit(new complex(0,0),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==151 || tmp.o==152) throw new Exception("wrong equation-wrong ternary equation");
                else if(tmp.o==153) {//?:
                    if((stacksize=stack1.size())<3) throw new Exception("wrong equation-incomplete formula");
                    if(stack1.get(stacksize-2).t!=null) stack1.remove(stacksize-1);
                    else stack1.remove(stacksize-2);
                    stack1.remove(stacksize-3);
                }
                else if(154<=tmp.o&&tmp.o<=165) {//**=
                    if((stacksize=stack1.size())<2) throw new Exception("wrong equation-incomplete formula");
                    unit v2=stack1.get(stacksize-2);
                    if(v2.t==null) throw new Exception("wrong equation-cannot modify rvalue");
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==171) {//,
                    if((stacksize=stack1.size())<2) throw new Exception("wrong equation-incomplete formula");
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==181) {stack1.clear();}//;
                else if(tmp.o==-1 || tmp.o==-2) throw new Exception("wrong equation-parenthesis mismatch");
                else throw new Exception("wrong equation-unknown error");
            }
        }
    }
    public String solve(equation in) throws Exception {
        stack1.clear();
        int eqs=in.eq.size();
        for(int i=0;i<eqs;i++) {
            unit tmp=in.eq.get(i);
            if(tmp.o==null) stack1.add(new unit(tmp));
            else {
                int stacksize;
                if(tmp.o==1) {//function call
                    stacksize=stack1.size(); unit v=stack1.get(stacksize-1); complex val=v.c;
                    if(val==null) val=get(v.t);
                    complex result=null;//v.commastack.get(1...), val
                    if(tmp.t.equals("$max")) {
                        complex temp=null;
                        if(val.isreal()) temp=val;
                        if(v.commalog!=null) {
                            int js=v.commalog.size();
                            for(int j=1;j<js;j++) {
                                complex tm=v.commalog.get(j);
                                if(tm.isreal()&&(temp==null||temp.r<tm.r)) temp=tm;
                            }
                        }
                        if(temp==null) result=new complex(Double.NaN,Double.NaN);
                        else result=temp;
                    }
                    else if(tmp.t.equals("$min")) {
                        complex temp=null;
                        if(val.isreal()) temp=val;
                        if(v.commalog!=null) {
                            int js=v.commalog.size();
                            for(int j=1;j<js;j++) {
                                complex tm=v.commalog.get(j);
                                if(tm.isreal()&&(temp==null||temp.r>tm.r)) temp=tm;
                            }
                        }
                        if(temp==null) result=new complex(Double.NaN,Double.NaN);
                        else result=temp;
                    }
                    else if(tmp.t.equals("$rand")) { result=new complex(Math.random(),0);}
                    else if(tmp.t.equals("$isnan")) {
                        if(Double.isNaN(val.r)||Double.isNaN(val.r)||Double.isNaN(val.r)||Double.isNaN(val.r))
                            result=new complex(true);
                        else result=new complex(false);
                    }
                    else if(tmp.t.equals("$isreal")) {result=new complex(val.isreal());}
                    else if(tmp.t.equals("$round")) {
                        result=new complex();result.r=Math.round(val.r);result.i=Math.round(val.i);
                    }
                    else if(tmp.t.equals("$floor")) {
                        result=new complex();result.r=Math.floor(val.r);result.i=Math.floor(val.i);
                    }
                    else if(tmp.t.equals("$ceil")) {
                        result=new complex();result.r=Math.ceil(val.r);result.i=Math.ceil(val.i);
                    }
                    else if(tmp.t.equals("$gcd")) {
                        result=new complex(Double.NaN,Double.NaN);
                        if(v.commalog!=null&&v.commalog.size()>1) {
                            complex val2=v.commalog.get(1);
                            if(val.isreal() && val2.isreal()) {
                                complex v1r=val.round();complex v2r=val2.round();
                                if(v1r.isequal(resol,val)&&v2r.isequal(resol, val2)) {
                                    result.r= ints.gcd((int)v1r.r, (int)v2r.r);result.i=0;
                                }
                            }
                        }
                    }
                    else if(tmp.t.equals("$lcm")) {
                        result=new complex(Double.NaN,Double.NaN);
                        if(v.commalog!=null&&v.commalog.size()>1) {
                            complex val2=v.commalog.get(1);
                            if(val.isreal() && val2.isreal()) {
                                complex v1r=val.round();complex v2r=val2.round();
                                if(v1r.isequal(resol,val)&&v2r.isequal(resol, val2)) {
                                    result.r= ints.lcm((int)v1r.r, (int)v2r.r);result.i=0;
                                }
                            }
                        }
                    }
                    else if(tmp.t.equals("$real")) {result=new complex();result.r=val.r;result.i=0;}
                    else if(tmp.t.equals("$imag")) {result=new complex();result.r=val.i;result.i=0;}
                    else if(tmp.t.equals("$conj")) {result=new complex();result.r=val.r;result.i=-val.i;}
                    else if(tmp.t.equals("$arg")) {
                        result=new complex(); double arg;
                        if(val.i==0&&val.r<0) arg=Math.PI; else arg=Math.atan2(val.i,val.r);
                        result.r=arg;result.i=0;
                    }
                    else if(tmp.t.equals("$abs")) {result=new complex();result.r=Math.sqrt(val.r*val.r+val.i*val.i);result.i=0;}
                    else if(tmp.t.equals("$pow")) {
                        result=new complex();
                        if(v.commalog!=null && v.commalog.size()>1) result.store_pow(v.commalog.get(1), val);
                        else result=val.exp();
                    }
                    else if(tmp.t.equals("$log")) {
                        result=new complex();
                        if(v.commalog!=null&&v.commalog.size()>1) result.store_log(v.commalog.get(1), val);
                        else result.store_log(new complex(10,0), val);
                    }
                    else if(tmp.t.equals("$ln")) {result=val.ln();}
                    else if(tmp.t.equals("$exp")) {result=val.exp();}
                    else if(tmp.t.equals("$sqrt")) {result=val.sqrt();}
                    else if(tmp.t.equals("$cbrt")) {result=val.cbrt();}
                    else if(tmp.t.equals("$fact")) {result=val.fact();}
                    else if(tmp.t.equals("$sin")) {result=val.sin();}
                    else if(tmp.t.equals("$cos")) {result=val.cos();}
                    else if(tmp.t.equals("$tan")) {result=val.tan();}
                    else if(tmp.t.equals("$sec")) {result=val.sec();}
                    else if(tmp.t.equals("$csc")) {result=val.csc();}
                    else if(tmp.t.equals("$cot")) {result=val.cot();}
                    else if(tmp.t.equals("$asin")) {result=val.asin();}
                    else if(tmp.t.equals("$acos")) {result=val.acos();}
                    else if(tmp.t.equals("$atan")||tmp.t.equals("$atan2")) {
                        if(v.commalog!=null&&v.commalog.size()>1) {
                            result=new complex(0,0);
                            complex valy=v.commalog.get(1);
                            complex tmp1=new complex(); tmp1.store_mul(val, val);
                            complex tmp2=new complex();tmp2.store_mul(valy, valy);
                            complex sq=new complex();sq.store_add(tmp1,tmp2);sq=sq.sqrt();
                            if(sq.isequal(1e-9,new complex(-val.r,-val.i))) {//sq+x near 0? -> x<0
                                if(valy.r==0 && valy.i==0) valy.r=1e-20;
                                complex tmp3=new complex(sq.r-val.r,sq.i-val.i);
                                complex tmp4=new complex();tmp4.store_div(tmp3,valy);
                                complex at=tmp4.atan();result.r=2*at.r;result.i=2*at.i;
                            }
                            else {//x>0
                                complex tmp3=new complex(sq.r+val.r,sq.i+val.i);
                                complex tmp4=new complex();tmp4.store_div(valy,tmp3);
                                complex at=tmp4.atan();result.r=2*at.r;result.i=2*at.i;
                            }
                        }
                        else result=val.atan();
                    }
                    else if(tmp.t.equals("$acsc")) {
                        complex tm=new complex();tm.store_div(1, val);result=tm.asin();
                    }
                    else if(tmp.t.equals("$asec")) {
                        complex tm=new complex();tm.store_div(1, val);result=tm.acos();
                    }
                    else if(tmp.t.equals("$acot")) {
                        complex tm=new complex();tm.store_div(1, val);result=tm.atan();
                    }
                    else if(tmp.t.equals("$sinh")) {result=val.sinh();}
                    else if(tmp.t.equals("$cosh")) {result=val.cosh();}
                    else if(tmp.t.equals("$tanh")) {result=val.tanh();}
                    else if(tmp.t.equals("$sech")) {result=val.sech();}
                    else if(tmp.t.equals("$csch")) {result=val.csch();}
                    else if(tmp.t.equals("$coth")) {result=val.coth();}
                    else if(tmp.t.equals("$asinh")) {result=val.asinh();}
                    else if(tmp.t.equals("$acosh")) {result=val.acosh();}
                    else if(tmp.t.equals("$atanh")) {result=val.atanh();}
                    else if(tmp.t.equals("$acsch")) {
                        complex tm=new complex();tm.store_div(1, val);result=tm.asinh();
                    }
                    else if(tmp.t.equals("$asech")) {
                        complex tm=new complex();tm.store_div(1, val);result=tm.acosh();
                    }
                    else if(tmp.t.equals("$acoth")) {
                        complex tm=new complex();tm.store_div(1, val);result=tm.atanh();
                    }
                    stack1.set(stacksize-1,new unit(result,null,null));
                }
                else if(tmp.o==11) {//a++
                    stacksize=stack1.size();unit v1=stack1.get(stacksize-1);complex tmp2=get(v1.t);
                    stack1.set(stacksize-1,new unit(new complex(tmp2),null,null));
                    tmp2.isbool=false; tmp2.r+=1;
                }
                else if(tmp.o==12) {//a--
                    stacksize=stack1.size();unit v1=stack1.get(stacksize-1); complex tmp2=get(v1.t);
                    stack1.set(stacksize-1,new unit(new complex(tmp2),null,null));
                    tmp2.isbool=false; tmp2.r-=1;
                }
                else if(tmp.o==21) {//++a
                    stacksize=stack1.size();unit v1=stack1.get(stacksize-1);
                    complex tmp2=get(v1.t); tmp2.isbool=false; tmp2.r+=1;
                }
                else if(tmp.o==22) {//--a
                    stacksize=stack1.size();unit v1=stack1.get(stacksize-1);
                    complex tmp2=get(v1.t); tmp2.isbool=false; tmp2.r-=1;
                }
                else if(tmp.o==23) {//~
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val=v1.c; if(val==null) val=get(v1.t);
                    stack1.set(stacksize-1,new unit(new complex((double)(~((long)val.r)),0),null,null));
                }
                else if(tmp.o==24) {//!
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val=v1.c; if(val==null) val=get(v1.t);
                    if(val.istrue()) stack1.set(stacksize-1,new unit(new complex(false),null,null));
                    else stack1.set(stacksize-1,new unit(new complex(true),null,null));
                }
                else if(tmp.o==25) {//-a
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val=v1.c; if(val==null) val=get(v1.t);
                    stack1.set(stacksize-1,new unit(new complex(-val.r,-val.i),null,null));
                }
                else if(tmp.o==26) {//+a
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val=v1.c; if(val==null) val=get(v1.t);
                    stack1.set(stacksize-1,new unit(new complex(val.r,val.i),null,null));
                }
                else if(tmp.o==31) {//**
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex();ret.store_pow(val2, val1);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==41) {//*
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex();ret.store_mul(val2, val1);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==42) {// /
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex();ret.store_div(val2, val1);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==43) {// %
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex();ret.store_mod(val2, val1);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==51) {//+
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex();ret.store_add(val2, val1);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==52) {//-
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex();ret.store_sub(val2, val1);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==61) {//<<
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex((double)(((long)val2.r)<<((long)val1.r)),0);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==62) {//>>
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex((double)(((long)val2.r)>>((long)val1.r)),0);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==71) {//<
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val1.islarge(val2)) stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==72) {//>
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val2.islarge(val1)) stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==73) {//<=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val1.islargeorequal(resol, val2))stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==74) {//>=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val2.islargeorequal(resol, val1))stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==81) {//==
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val1.isequal(resol, val2)) stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==82) {//!=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val1.isequal(resol, val2)) stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==91) {//&
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex((double)(((long)val2.r)&((long)val1.r)),0);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==101) {//^
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex((double)(((long)val2.r)^((long)val1.r)),0);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==111) {//|
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    complex ret=new complex((double)(((long)val2.r)|((long)val1.r)),0);
                    stack1.set(stacksize-2,new unit(ret,null,null));stack1.remove(stacksize-1);
                }
                else if(tmp.o==121) {//&&
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val1.istrue()&&val2.istrue()) stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==131) {//^^
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(!val1.istrue()&&val2.istrue()||!val2.istrue()&&val1.istrue()) stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==141) {//||
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(val1.istrue()||val2.istrue()) stack1.set(stacksize-2,new unit(new complex(true),null,null));
                    else stack1.set(stacksize-2,new unit(new complex(false),null,null));
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==153) {//?:
                    stacksize=stack1.size();
                    unit v3=stack1.get(stacksize-3); complex val3=v3.c; if(val3==null) val3=get(v3.t);
                    if(val3.istrue()) stack1.remove(stacksize-1);
                    else stack1.remove(stacksize-2);
                    stack1.remove(stacksize-3);
                }
                else if(tmp.o==154) {//=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    val2.r=val1.r;val2.i=val1.i;val2.isbool=val1.isbool;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==155) {//*=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.store_mul(tm1, val1);
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==156) {// /=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.store_div(tm1, val1);
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==157) {// %=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.store_mod(tm1, val1);
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==158) {// +=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.store_add(tm1, val1);
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==159) {// -=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.store_sub(tm1, val1);
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==160) {// <<=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.r=(double)(((long)tm1.r)<<((long)val1.r));val2.i=0;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==161) {// >>=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.r=(double)(((long)tm1.r)>>((long)val1.r));val2.i=0;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==162) {// &=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.r=(double)(((long)tm1.r)&((long)val1.r));val2.i=0;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==163) {// |=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.r=(double)(((long)tm1.r)|((long)val1.r));val2.i=0;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==164) {// ^=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.r=(double)(((long)tm1.r)^((long)val1.r));val2.i=0;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==165) {//**=
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1); complex val1=v1.c; if(val1==null) val1=get(v1.t);
                    unit v2=stack1.get(stacksize-2); complex val2=get(v2.t);
                    complex tm1=new complex(val2);
                    val2.store_pow(tm1, val1);
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==171) {//,
                    stacksize=stack1.size();
                    unit v1=stack1.get(stacksize-1);
                    unit v2=stack1.get(stacksize-2); complex val2=v2.c; if(val2==null) val2=get(v2.t);
                    if(v2.commalog==null) v2.commalog=new ArrayList<>();
                    v2.commalog.add(new complex(val2));v2.c=v1.c;v2.o=v1.o;v2.t=v1.t;
                    stack1.remove(stacksize-1);
                }
                else if(tmp.o==181) {stack1.clear();}//;
            }
        }
        if(stack1.size()==0) return "";
        else {
            unit res=stack1.get(0);
            if(res.c!=null) return res.c.tostr(resol); else return get(res.t).tostr(resol);
        }
    }
    public String compileandsolve(String in) throws Exception{
        return solve(compile(in));
    }
}
