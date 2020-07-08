/*
 *  Program EdytorGraficzny
 *
 *  Autor: Wojciech Mielczarek
 *   Data: 6 grudnia 2015r.
 */

//Importy
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * @author Pawel Rogalisnki/Wojciech Mielczarek
 * @version 6 grudzia 2015 r.
 */
abstract class Figura implements Serializable{
	/** Atrubut przechowuj�cy informacj� o zaznaczeniu figury. */
	private boolean zaznaczona = false;
	/** Atrybut przechowuj�cy styl figury. 0 - kraw�d� / 1 - wype�nienie. */
	protected int styl = 0;
	/** Atrybut przechowyj�cy kolor figury. */
	protected Color kolor = Color.BLACK;

	/**
	 * Metoda zwraca informacj� o zaznaczeniu figury.
	 *
	 * @return stan figury
	 */
    public boolean jestZaznaczona(){ return zaznaczona; }

    /**
     * Metoda umo�liwia zaznaczenie figury.
     */
    public void zaznacz(){ zaznaczona = true; };

    /**
     * Metoda umo�liwia nadanie figurze zaznaczenia lub niezaznaczenia.
     *
     * @param z true/false, stan zaznaczenia
     */
    public void zaznacz(boolean z){ zaznaczona = z; }

    /**
     * Metoda umo�liwia odznaczenie figury.
     */
    public void odznacz(){ zaznaczona = false; }

    /**
     * Metoda umo�liwia odwr�cenie zaznaczenia figury.
     */
    public void zaznaczOdwrotnie(){ zaznaczona = !zaznaczona; }

	/**
	 * Metoda abstrakcyjna, po zdefiniowaniu w klasie pochodnej b�dzie zwraca�a informacj� o tym, czy kursor jest wewn�trz figury.
	 *
	 * @param px po�o�enie X kursora
	 * @param py po�o�enie Y kursora
	 */
    public abstract boolean jestWewnatrz(float px, float py);

    /**
     * Metoda zwraca informacj� o tym, czy kursor jest wewn�trz figury.
     *
     * @param px po�o�enie X kursora
     * @param py po�o�enie Y kursora
     *
     * @return true - kursor wewn�trz / false - kursor na zewn�trz figury.
     */
    public boolean jestWewnatrz(int px, int py){ return jestWewnatrz((float)px, (float)py); }

	/**
	 * Metoda abstrakcyjna, po zdefiniowaniu w klasie pochodnej b�dzie odpowiedzialna za obliczanie pola figury.
	 */
    public abstract float pole();

    /**
     * Metoda abstrakcyjna, po zdefiniowaniu w klasie pochodnej b�dzie odpowiedzialna za obliczanie obwodu figury.
     */
    public abstract float obwod();

	/**
	 * Metoda abstrakcyjna, po zdefiniowaniu w klasie pochodnej b�dzie odpowiedzialna za przesuwanie figury.
	 *
	 * @param dx przyrost po�o�enia na osi X
	 * @param dy przyrost po�o�enia na osi Y
	 */
    public abstract void przesun(float dx, float dy);

    /**
     * Metoda abstrakcyjna, po zdefiniowaniu w klasie pochodnej b�dzie odpowiedzialna za skalowanie figury.
     *
     * @param s skala
     */
    public abstract void skaluj(float s);

	/**
	 * Metoda umo�liwia ustawienie koloru figury.
	 *
	 * @param g obiekt graficzny
	 */
    protected void ustawKolor(Graphics g){
    	if(zaznaczona) g.setColor(Color.MAGENTA);
        else g.setColor(kolor);
    };

	/**
	 * Metoda abstrakcyjna, po zdefiniowaniu w klasie pochodnej b�dzie odpowiedzialna za rysowanie figury.
	 *
	 * @param g obiekt graficzny
	 */
    public abstract void rysuj(Graphics g);

	/** Metoda umo�liwia zapisanie w�a�ciwo�ci figury - punkt�w, obwodu, pola, wymiar�w - w postaci ciagu znak�w.
	 *
	 * @return ci�g znak�w zawieraj�cy w�a�ciwo�ci figury
	 */
    protected String wlasciwosci(){
    	String s = "  Pole: " + String.format("%.2f", pole()) + "  Obw�d: " + String.format("%.2f", obwod());
      	if(jestZaznaczona()) s = s + "   [ZAZNACZONA]";
		if(styl == 1) s = s + "   [WYPE�NIONA]";
      	return s;
    }
}

/**
 * Klasa <code>Punkt</code> impelemntuje klas� obiekt�w geometrycznych - punkt�w.
 *
 * @author Pawe� Rogali�ski / Micha� Kaczara
 * @version 6 grudzie� 2010 r.
 */
class Punkt extends Figura{
	/** Atrybut przechowuj�cy wsp�rz�dn� X punktu */
	protected float x;
	/** Atrybut przechowuj�cy wsp�rz�dn� Y punktu */
	protected float y;

	/**
	 * Konstruktor wieloargumentowy. Tworzy punkt o danych wsp�rz�dnych i kolorze.
	 *
	 * @param px wsp�rz�dna X punktu
	 * @param py wsp�rz�dna Y punktu
	 * @param c kolor punktu
	 */
    public Punkt(float px, float py, Color c){
    	x=px;
    	y=py;
    	styl = 0;
    	kolor = c;
    }

	/**
	 * Metoda zwraca informacj� o tym, czy kursor jest wewn�trz punktu.
	 *
	 * @param px - pozycja X kursora
	 * @param py - pozycja Y kursora
	 *
	 * @return true - kursor wewn�trz / false - kursor na zewn�trz punktu.
	 */
    public boolean jestWewnatrz(float px, float py){
    	// by umozliwic zaznaczanie punktu myszka
      	// miejsca odlegle nie wiecej niz 3 leza wewnatrz
      	return(Math.sqrt((x-px)*(x-px) + (y-py)*(y-py))<=6);
    }

    /**
     * Metoda zwraca pole punktu.
     *
     * @return pole punktu = 0
     */
    public float pole(){ return 0; };

    /**
     * Metoda zwraca obw�d punktu.
     *
     * @return obw�d punktu = 0
     */
    public float obwod(){ return 0; };

    /**
     * Metoda umo�liwia przesuni�cie punktu.
     *
     * @param dx przyrost po�o�enia na osi X
     * @param dy przyrost po�o�enia na osi Y
     */
    public void przesun(float dx, float dy){ x+=dx; y+=dy; }

    /**
     * Metoda umo�liwia skalowanie punktu. Dla obiektu tego typu jest bezu�yteczna - nic nie robi.
     *
     * @param s skala
     */
    public void skaluj(float s){ };

	/**
	 * Metoda umo�liwia konwersj� wsp�rz�dnych punktu do postaci ci�gu znak�w.
	 *
	 * @return ci�g znak�w zawieraj�cy wsp�rz�dne punktu
	 */
    protected String toStringXY(){ return "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ")"; }

	/**
	 * Metoda umo�liwia konwersj� obiektu klasy <code>Punkt</code> do postaci ci�gu znak�w.
	 *
	 * @return ci�g znak�w opisuj�cy obiekt
	 */
    public String toString(){
    	return "Punkt " + toStringXY() + wlasciwosci();
    }

	/**
	 * Metoda umo�liwia rysowanie punktu na ekranie.
	 *
	 * @param g obiekt graficzny
	 */
    public void rysuj(Graphics g){
    	ustawKolor(g);
      	g.fillOval((int)(x-3),(int)(y-3), 6,6);
    };
}

/**
 * Klasa <code>Trojkat</code> impelemntuje klas� obiekt�w geometrycznych - tr�jk�t�w.
 *
 * @author Pawe� Rogali�ski / Micha� Kaczara
 * @version 6 grudzie� 2010 r.
 */
class Trojkat extends Figura
{
	/** Obiekt klasy <code>Punkt</code> - pierwszy wierzcho�ek tr�jk�ta */
	private Punkt punkt1;
	/** Obiekt klasy <code>Punkt</code> - drugi wierzcho�ek tr�jk�ta */
	private Punkt punkt2;
	/** Obiekt klasy <code>Punkt</code> - trzeci wierzcho�ek tr�jk�ta */
	private Punkt punkt3;

	/**
	 * Konstruktor wieloargumentowy. Tworzy tr�jk�t o podanych trzech wierzcho�kach, stylu i kolorze.
	 *
	 * @param p1 pierwszy wierzcho�ek tr�jk�ta
	 * @param p2 drugi wierzcho�ek tr�jk�ta
	 * @param p3 trzeci wierzcho�ek tr�jk�ta
	 * @param st styl tr�jk�ta. 0 - kraw�d� / 1 - wype�nienie
	 */
    public Trojkat(Punkt p1, Punkt p2, Punkt p3, int st, Color c){
    	punkt1=p1;
    	punkt2=p2;
    	punkt3=p3;
    	styl = st;
    	kolor = c;
    }

	/**
	 * Metoda zwraca informacj� o tym, czy kursor jest wewn�trz tr�jk�ta.
	 *
	 * @param px - pozycja X kursora
	 * @param py - pozycja Y kursora
	 *
	 * @return true - kursor wewn�trz / false - kursor na zewn�trz tr�jk�ta
	 */
    public boolean jestWewnatrz(float px, float py){
    	float d1, d2, d3;
     	d1 = px*(punkt1.y-punkt2.y) + py*(punkt2.x-punkt1.x) +
           	(punkt1.x*punkt2.y-punkt1.y*punkt2.x);
      	d2 = px*(punkt2.y-punkt3.y) + py*(punkt3.x-punkt2.x) +
           	(punkt2.x*punkt3.y-punkt2.y*punkt3.x);
      	d3 = px*(punkt3.y-punkt1.y) + py*(punkt1.x-punkt3.x) +
           	(punkt3.x*punkt1.y-punkt3.y*punkt1.x);

      	return ((d1<=0)&&(d2<=0)&&(d3<=0)) || ((d1>=0)&&(d2>=0)&&(d3>=0));
    }

	/**
	 * Metoda zwraca pole tr�jk�ta.
	 *
	 * @return pole tr�jk�ta
	 */
    public float pole(){
    	float a = (float)Math.sqrt( (punkt1.x-punkt2.x)*(punkt1.x-punkt2.x)+
                                    (punkt1.y-punkt2.y)*(punkt1.y-punkt2.y));
        float b = (float)Math.sqrt( (punkt2.x-punkt3.x)*(punkt2.x-punkt3.x)+
                                    (punkt2.y-punkt3.y)*(punkt2.y-punkt3.y));
        float c = (float)Math.sqrt( (punkt1.x-punkt3.x)*(punkt1.x-punkt3.x)+
                                    (punkt1.y-punkt3.y)*(punkt1.y-punkt3.y));
        float p=(a+b+c)/2;

        return (float)Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

	/**
	 * Metoda zwraca obw�d tr�jk�ta.
	 *
	 * @return obw�d tr�jk�ta
	 */
    public float obwod(){
    	float a = (float)Math.sqrt( (punkt1.x-punkt2.x)*(punkt1.x-punkt2.x)+
                                    (punkt1.y-punkt2.y)*(punkt1.y-punkt2.y));
        float b = (float)Math.sqrt( (punkt2.x-punkt3.x)*(punkt2.x-punkt3.x)+
                                    (punkt2.y-punkt3.y)*(punkt2.y-punkt3.y));
        float c = (float)Math.sqrt( (punkt1.x-punkt3.x)*(punkt1.x-punkt3.x)+
                                    (punkt1.y-punkt3.y)*(punkt1.y-punkt3.y));

        return a+b+c;
    }

    /**
     * Metoda umo�liwia przesuni�cie tr�jk�ta.
     *
     * @param dx przyrost po�o�enia na osi X
     * @param dy przyrost po�o�enia na osi Y
     */
    public void przesun(float dx, float dy){
    	punkt1.przesun(dx,dy);
        punkt2.przesun(dx,dy);
        punkt3.przesun(dx,dy);
    }

    /**
     * Metoda umo�liwia skalowanie tr�jk�ta.
     *
     * @param s skala
     */
    public void skaluj(float s){
    	Punkt sr1 = new Punkt((punkt1.x+punkt2.x+punkt3.x)/3,
                              (punkt1.y+punkt2.y+punkt3.y)/3, kolor);
        punkt1.x*=s; punkt1.y*=s;
        punkt2.x*=s; punkt2.y*=s;
        punkt3.x*=s; punkt3.y*=s;
        Punkt sr2 = new Punkt((punkt1.x+punkt2.x+punkt3.x)/3,
                              (punkt1.y+punkt2.y+punkt3.y)/3, kolor);
        float dx=sr1.x-sr2.x;
        float dy=sr1.y-sr2.y;
        punkt1.przesun(dx,dy);
        punkt2.przesun(dx,dy);
        punkt3.przesun(dx,dy);
    }

	/**
	 * Metoda umo�liwia konwersj� obiektu klasy <code>Trojkat</code> do postaci ci�gu znak�w.
	 *
	 * @return ci�g znak�w opisuj�cy obiekt
	 */
    public String toString(){
    	return "Tr�jk�t {" + punkt1.toStringXY() + ";" + punkt2.toStringXY() + ";" + punkt3.toStringXY() + "}" + wlasciwosci();
    }

	/**
	 * Metoda umo�liwia rysowanie tr�jk�ta na ekranie.
	 *
	 * @param g obiekt graficzny
	 */
    public void rysuj(Graphics g){
    	ustawKolor(g);
    	if(styl == 1){
    		Polygon p = new Polygon();
    		p.addPoint((int)punkt1.x, (int)punkt1.y);
    		p.addPoint((int)punkt2.x, (int)punkt2.y);
    		p.addPoint((int)punkt3.x, (int)punkt3.y);

    		g.fillPolygon(p);
    	}
    	else{
        g.drawLine((int)punkt1.x, (int)punkt1.y,
                   (int)punkt2.x, (int)punkt2.y);
        g.drawLine((int)punkt2.x, (int)punkt2.y,
                   (int)punkt3.x, (int)punkt3.y);
        g.drawLine((int)punkt3.x, (int)punkt3.y,
                   (int)punkt1.x, (int)punkt1.y);
    	}
    }
}

/**
 * Klasa <code>Kolo</code> impelemntuje klas� obiekt�w geometrycznych - k�.
 *
 * @author Wojciech Mielczarek
 * @version 6 grudzia 2015 r.
 */
class Kolo extends Punkt{
	/** Atrybut przechowuj�cy promie� ko�a */
	private float r;

	/**
	 * Konstruktor wieloargimentowy. Tworzy ko�o o danych wsp�rz�dnych �rodka, promieniu, stylu i kolorze.
	 *
	 * @param sX ws�rz�dna X �rodka ko�a
	 * @param sY wsp�rz�dna Y �rodka ko�a
	 * @param er promie�
	 * @param st styl obiektu. 0 - kraw�d�(okr�g) / 1 - wype�nienie(ko�o)
	 * @param c kolor ko�a
	 */
	public Kolo(float sX, float sY, float er, int st, Color c){
		super(sX, sY, c);
		r = er;
		styl = st;
	}

	/**
	 * Metoda zwraca informacj� o tym, czy kursor jest wewn�trz ko�a.
	 *
	 * @param px - pozycja X kursora
	 * @param py - pozycja Y kursora
	 *
	 * @return true - kursor wewn�trz / false - kursor na zewn�trz ko�a
	 */
    public boolean jestWewnatrz(float px, float py){
    	//R�wnanie ko�a
		return (Math.sqrt((x-px)*(x-px) + (y-py)*(y-py)) <= r);
    }

	/**
	 * Metoda zwraca pole ko�a.
	 *
	 * @return pole ko�a
	 */
    public float pole(){
    	//PI*r^2
    	return ((float)3.14*(r*r));
    }

	/**
	 * Metoda zwraca obw�d ko�a.
	 *
	 * @return obw�d ko�a
	 */
    public float obwod(){
    	//2*PI*r
    	return(2*(float)3.14*r);
    }

    /**
     * Metoda umo�liwia skalowanie ko�a.
     *
     * @param s skala
     */
    public void skaluj(float s){
    	r = r*s;
    }

	/**
	 * Metoda umo�liwia konwersj� obiektu klasy <code>Kolo</code> do postaci ci�gu znak�w.
	 *
	 * @return ci�g znak�w opisuj�cy obiekt
	 */
    public String toString(){
		return "Ko�o {" + toStringXY() + "," + String.format("%.2f", r) + "}" + wlasciwosci();
    }

	/**
	 * Metoda umo�liwia rysowanie ko�a na ekranie.
	 *
	 * @param g obiekt graficzny
	 */
    public void rysuj(Graphics g){
		ustawKolor(g);
		if(styl == 1) g.fillOval((int)(x-r), (int)(y-r), (int)(2.0*r), (int)(2.0*r));
		else g.drawOval((int)(x-r), (int)(y-r), (int)(2.0*r), (int)(2.0*r));
    }
}

/**
 * Klasa <code>Prostokat</code> impelemntuje klas� obiekt�w geometrycznych - prostok�t�w.
 *
 * @author Micha� Kaczara
 * @version 6 grudzie� 2010 r.
 */
class Prostokat extends Punkt{
	/** Atrybut przechowuj�cy wysoko�� prostok�ta */
	private float wys;
	/** Atrybut przechowuj�cy szeroko�� prostok�ta */
	private float szer;

	/**
	 * Konstruktor wieloargumentowy. Tworzy prostok�t o podanych ws�rz�dnych �rodka, wysko�ci, szeroko�ci, stylu i kolorze.
	 *
	 * @param sX wsp�rz�dna X �rodka
	 * @param sY wsp�rz�dna Y �rodka
	 * @param wy wysoko�� prostok�ta
	 * @param sz szeroko�� prostok�ta
	 * @param st styl obiektu. 0 - kraw�d� / 1 - wype�nienie
	 * @param c kolor prostok�ta
	 */
	public Prostokat(float sX, float sY, float wy, float sz, int st, Color c){
		super(sX, sY, c);
		wys = wy;
		szer = sz;
		styl = st;
	}

	/**
	 * Metoda zwraca informacj� o tym, czy kursor jest wewn�trz prostok�ta.
	 *
	 * @param px - pozycja X kursora
	 * @param py - pozycja Y kursora
	 *
	 * @return true - kursor wewn�trz / false - kursor na zewn�trz prostok�ta
	 */
	public boolean jestWewnatrz(float px, float py){
		return ((py >= y) && (py <= y+wys) && (px >= x) && (px <= x+szer));
	}

	/**
	 * Metoda zwraca pole prostok�ta.
	 *
	 * @return pole prostok�ta
	 */
	public float pole(){
		return (wys*szer);
	}

	/**
	 * Metoda zwraca obw�d prostok�ta.
	 *
	 * @return obw�d prostok�ta
	 */
	public float obwod(){
		return (2*wys + 2*szer);
	}

    /**
     * Metoda umo�liwia skalowanie prostok�ta.
     *
     * @param s skala
     */
	public void skaluj(float s){
		wys = wys*s;
		szer = szer*s;
	}

	/**
	 * Metoda umo�liwia konwersj� obiektu klasy <code>Prostokat</code> do postaci ci�gu znak�w.
	 *
	 * @return ci�g znak�w opisuj�cy obiekt
	 */
	public String toString(){
		return "Prostok�t {" + toStringXY() + "; " + String.format("%.2f", wys) + "; " + String.format("%.2f", szer) + "}" + wlasciwosci();
	}

	/**
	 * Metoda umo�liwia rysowanie prostok�ta na ekranie.
	 *
	 * @param g obiekt graficzny
	 */
	public void rysuj(Graphics g){
		ustawKolor(g);
		if(styl == 1) g.fillRect((int)x, (int)y, (int)szer, (int)wys);
		else g.drawRect((int)x, (int)y, (int)szer, (int)wys);
	}
}

/**
 * Klasa <code>Rysunek</code> implementuje metody odpowiedzialne za rysowanie i wy�wietlanie obiekt�w oraz obs�ug� myszy i klawiatury.
 *
 * @author Pawe� Rogali�ski / Wojciech Mielczarek
 * @version 6 grudzia 2015 r.
 */
class Rysunek extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Serializable{
	/** Lista figur */
	ArrayList<Figura> figury = new ArrayList();
	/** Atrybut przechowuj�cy aktualn� pozycj� X kursora */
	private int myszX;
	/** Atrybut przechowuj�cy aktualn� pozycj� Y kursora */
	private int myszY;
	/** Atrybut przechowuj�cy aktualny styl rysunku. 0 - kraw�d� / 1 - wype�nienie */
	private int styl = 0;
	/** Atrybut przechowuj�cy aktualny kolor rysunku */
	private Color kolor = Color.BLACK;

	/**
	 * Metoda umo�liwia ustawienie aktualnego stylu rysunku.
	 *
	 * @param st styl
	 */
	public void ustawStyl(int st){
		styl = st;
	}

	/**
	 * Metoda umo�liwia ustawienie aktualnego koloru rysunku.
	 *
	 * @param c kolor
	 */
	public void ustawKolor(Color c){
		kolor = c;
	}

	/**
	 * Metoda umo�liwia czyszczenie rysunku - usuni�cie wszystkich figur z listy.
	 */
	public void czysc(){
		figury.clear();
		repaint();
	}

	/**
	 * Metoda umo�liwia dodanie figury do rysunku.
	 *
	 * @param fig figura
	 */
    private void dodajFigure(Figura fig){
     	for (Figura f : figury){ f.odznacz(); }
      	fig.zaznacz();
      	figury.add(fig);
      	repaint();
    }

	/**
	 * Metoda umo�liwia dodanie obiektu klasy <code>Punkt</code> do rysunku.
	 */
    public void dodajPunkt(){
    	float x, y;
     	Random random = new Random();
      	x = random.nextFloat()*getWidth();
      	y = random.nextFloat()*getHeight();

      	dodajFigure(new Punkt(x, y, kolor));
    }

	/**
	 * Metoda umo�liwia dodanie obiektu klasy <code>Trojkat</code> do rysunku.
	 */
    public void dodajTrojkat(){
    	float x1, y1, x2, y2, x3, y3;
      	Random random = new Random();
      	x1 = random.nextFloat()*getWidth();
      	y1 = random.nextFloat()*getHeight();
      	x2 = random.nextFloat()*getWidth();
      	y2 = random.nextFloat()*getHeight();
      	x3 = random.nextFloat()*getWidth();
      	y3 = random.nextFloat()*getHeight();

      	dodajFigure(new Trojkat(new Punkt(x1,y1, kolor),new Punkt(x2,y2, kolor), new Punkt(x3,y3, kolor), styl, kolor));
    }

	/**
	 * Metoda umo�liwia dodanie obiektu klasy <code>Kolo</code> do rysunku.
	 */
	public void dodajKolo(){
		float sX, sY, r;
		Random random = new Random();
		sX = random.nextFloat()*getWidth();
		sY = random.nextFloat()*getHeight();
		r = random.nextFloat()*20+15;

		dodajFigure(new Kolo(sX, sY, r, styl, kolor));
	}

	/**
	 * Metoda umo�liwia dodanie obiektu klasy <code>Prostokat</code> do rysunku.
	 */
	public void dodajProstokat(){
		float sX, sY, wy, sz;
		Random random = new Random();
		sX = random.nextFloat()*getWidth();
		sY = random.nextFloat()*getHeight();
		wy = random.nextFloat()*20+15;
		sz = random.nextFloat()*20+15;

		dodajFigure(new Prostokat(sX, sY, wy, sz, styl, kolor));
	}

	/**
	 * Metoda umo�liwia przesuni�cie zaznaczonych figur.
	 *
	 * @param dx przyrost po�o�enia na osi X
	 * @param dy przyrost po�o�enia na osi Y
	 */
    void przesun(float dx, float dy){
    	for (Figura f : figury){
    		if (f.jestZaznaczona()) f.przesun(dx,dy);
    	}
      	repaint();
    }

	/**
	 * Metoda umo�liwia skalowanie zaznaczonych figur.
	 *
	 * @param s skala
	 */
    void skaluj(float s)
    {
    	for (Figura f : figury){
    		if (f.jestZaznaczona()) f.skaluj(s);
    	}
      	repaint();
    }

	/**
	 * Metoda umo�liwia konwersj� obiektu klasy <code>Rysunek</code> do postaci ci�gu znak�w.
	 *
	 * @return ci�g znak�w opisuj�cy obiekt
	 */
    public String toString()
    {
    	String s = "Rysunek{\n";
      	for (Figura f : figury){
      		s += "         " + f.toString() + "\n";
      	}
      	s+="}";

      	return s;
    }

	/**
	 * Metoda umo�liwia wyrysowanie zawarto�ci rysunku na ekranie.
	 *
	 * @param g obiekt graficzny
	 */
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
        for (Figura f : figury)
        	f.rysuj(g);
    }

     /**
      * Metoda obs�uguje zdarzenie, gdy wciskany jest klawisz, w ramach s�uchacza zdarze� <code>KeyListener</code>.
      *
      * @param evt zdarzenie wy�apane przez s�uchacza zdarze�
      */
     public void keyPressed(KeyEvent evt){
     //Virtual keys (arrow keys, function keys, etc) - handled with keyPressed() listener.
     	int krok;
        if (evt.isShiftDown()) krok = 10;
        else krok = 1;

        switch (evt.getKeyCode()){
       		case KeyEvent.VK_LEFT : przesun(-krok, 0); break;
            case KeyEvent.VK_RIGHT: przesun( krok, 0); break;
            case KeyEvent.VK_UP   : przesun(0, -krok); break;
            case KeyEvent.VK_DOWN : przesun(0,  krok); break;
            case KeyEvent.VK_DELETE :
           		Iterator<Figura> i = figury.iterator();
            	while(i.hasNext()){
            		Figura f = i.next();
            	  	if(f.jestZaznaczona()) i.remove();
            	}
            	repaint();
                break;
         }
     }

    /**
     * Metoda obs�uguje zdarzenie, gdy zwalniany jest klawisz, w ramach s�uchacza zdarze� <code>KeyListener</code>.
     *
     * @param evt zdarzenie wy�apane przez s�uchacza zdarze�
     */
    public void keyReleased(KeyEvent evt){
       	char znak=evt.getKeyChar(); //reakcja na przycisku na naci�ni�cie klawisza
      	switch(znak){
        	case 'k': dodajKolo(); break;
        	case 'r': dodajProstokat(); break;
        	case 'C': kolor = Color.BLACK; break;
        	case 'R': kolor = Color.RED; break;
        	case 'G': kolor = Color.GREEN; break;
        	case 'B': kolor = Color.BLUE; break;
        	case 'Y': kolor = Color.YELLOW; break;
      	}
    }

    /**
     * Metoda obs�uguje zdarzenie, gdy wcisni�ty zosta� klawisz, w ramach s�uchacza zdarze� <code>KeyListener</code>.
     *
     * @param evt zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void keyTyped(KeyEvent evt){
    	//Characters (a, A, #, ...) - handled in the keyTyped() listener.
      	char znak=evt.getKeyChar(); //reakcja na przycisku na naci�ni�cie klawisza
      	switch(znak){
      		case 'p': dodajPunkt(); break;
        	case 't': dodajTrojkat(); break;
        	case 'n': czysc(); break;
        	case 's': zapisz(); break;
        	case 'o': wczytaj(); break;
      	}
     	int nr = (int)znak-48; // obsluga klawiszy '0'...'9'
      	if(nr>=0 && nr<figury.size()){
        	if (evt.isAltDown()==false) for(Figura f : figury ) f.odznacz();
          	figury.get(nr).zaznaczOdwrotnie();
          	repaint();
        }

        if(znak == '+') skaluj((float)1.1);
        else if(znak == '-') skaluj((float)0.9);
    }

    /**
     * Metoda obs�uguje zdarzenie, gdy wcisni�ty zosta� przycisk myszy, w ramach s�uchacza zdarze� <code>Mouseistener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mouseClicked(MouseEvent e)
    // Invoked when the mouse button has been clicked (pressed and released) on a component.
    {
    	int px = e.getX();
      	int py = e.getY();
      	for(Figura f : figury){
        	if (e.isAltDown()==false) f.odznacz();
          	if (f.jestWewnatrz(px,py)) f.zaznaczOdwrotnie();
        }
      	repaint();
    }

    /**
     * Metoda obs�uguje zdarzenie, gdy kursor znajduje si� nad obiektem, w ramach s�uchacza zdarze� <codeMouseListener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mouseEntered(MouseEvent e){
    //Invoked when the mouse enters a component.
	}

    /**
     * Metoda obs�uguje zdarzenie, gdy kursor znajduje si� po za obiektem, w ramach s�uchacza zdarze� <code>MouseListener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mouseExited(MouseEvent e){
    //Invoked when the mouse exits a component.
	}

    /**
     * Metoda obs�uguje zdarzenie, gdy wcisni�ty jest przycisk myszy, w ramach s�uchacza zdarze� <code>MouseListener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mousePressed(MouseEvent e){
    //Invoked when a mouse button has been pressed on a component.
    	myszX = e.getX();
    	myszY = e.getY();
    }

    /**
     * Metoda obs�uguje zdarzenie, gdy puszczony zosta� przycisk myszy, w ramach s�uchacza zdarze� <code>MouseListener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mouseReleased(MouseEvent e){
    //Invoked when a mouse button has been released on a component.
    }

    /**
     * Metoda obs�uguje zdarzenie, gdy kursor muszy zmieni� po�o�enie i przycisk by� wci�ni�ty, w ramach s�uchacza zdarze� <code>MouseListener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mouseDragged(MouseEvent e){
    	przesun(e.getX()-myszX, e.getY()-myszY);

     	myszX = e.getX();
    	myszY = e.getY();
    }

    /**
     * Metoda obs�uguje zdarzenie, gdy kursor muszy zmieni� po�o�enie, w ramach s�uchacza zdarze� <code>MouseListener</code>.
     *
     * @param e zdarzenie wy�apane przez s�uchacza zdarze�
    */
    public void mouseMoved(MouseEvent e){
    }

	/**
	 * Metoda umo�liwia zapis rysunku do pliku binarnego *.edg.
	 */
	public void zapisz(){
		String nazwaPliku = "";
		do{
			nazwaPliku = JOptionPane.showInputDialog(this, "Podaj nazw� pliku(bez rozszerzenia):", "Nazwa pliku", JOptionPane.QUESTION_MESSAGE);
			if(nazwaPliku.equals("")) JOptionPane.showMessageDialog(null, "Podaj poprawn� nazw� pliku", "B��d", JOptionPane.ERROR_MESSAGE);
		}
		while(nazwaPliku.equals(""));
		nazwaPliku += ".edg";

		FileOutputStream plik_wyj = null;
		ObjectOutputStream wyj = null;
		try{
			plik_wyj = new FileOutputStream(nazwaPliku);
			wyj = new ObjectOutputStream(plik_wyj);
			wyj.writeObject(this);
			wyj.flush();
			wyj.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
			return;
		}
	}

	/**
	 * Metoda umo�liwia odczyt rysunku z pliku binarnego *.edg.
	 *
	 * @return obiekt z wczytanym rysunkiem
	 */
	public Rysunek wczytaj(){
			String nazwaPliku = "";
			do{
				nazwaPliku = JOptionPane.showInputDialog(this, "Podaj nazw� pliku(bez rozszerzenia):", "Nazwa pliku", JOptionPane.QUESTION_MESSAGE);
				if(nazwaPliku.equals("")) JOptionPane.showMessageDialog(null, "Podaj poprawn� nazw� pliku", "B��d", JOptionPane.ERROR_MESSAGE);
			}
			while(nazwaPliku.equals(""));
			nazwaPliku += ".edg";

   			FileInputStream plik_we = null;
   			ObjectInputStream we = null;
   			Rysunek tmp = null;
   			try{
     			plik_we = new FileInputStream(nazwaPliku);
    			we = new ObjectInputStream(plik_we);
     			tmp = (Rysunek)we.readObject();
    			we.close();
   			}
   			catch(IOException ex){
     			ex.printStackTrace();
     			return this;
   			}
   			catch(ClassNotFoundException ex){
     			ex.printStackTrace();
     			return this;
   			}

   			return tmp;
	}
}

/**
 * Klasa <code>EdytorGraficzny</code> implementuje program b�d�cy prostym edytorem graficznym.
 *
 * <p>
 * Mo�liwo�ci programu:
 * <ul>
 * <li>tworzenie rysunk�w sk�adaj�cych si� z punkt�w, tr�jk�t�w, k� i prostok�t�w,</li>
 * <li>zmiana koloru(5 do wyboru) i stylu rysowania(kraw�d�/wype�nienie),</li>
 * <li>skalowanie i przesuwanie figur,</li>
 * <li>zapisywanie/wczytywanie rysunku do/z pliku binarnego *.edg,</li>
 * <li>sterowanie myszk� i/lub klawiatur�,</li>
 * <li>tworzenie zestawienia figur, kt�re zawiera rysunek.</li>
 * </ul>
 * </p>
 *
 * @author Pawe� Rogali�ski / Wojciech Mielczarek
 * @version 6 grudzie� 2010 r.
 */
public class EdytorGraficzny extends JFrame implements ActionListener
{
	/** Obiekt klasy <code>Rysunek</code> */
	protected Rysunek rysunek;

	/** Przycisk odpowiedzialny za dodanie punktu do rysunku. */
    private JButton przyciskPunkt = new JButton ("Punkt");
    /** Przycisk odpowiedzialny za dodanie tr�jk�ta do rysunku. */
    private JButton przyciskTrojkat = new JButton("Trojkat");
    /** Przycisk odpowiedzialny za dodanie ko�a do rysunku. */
    private JButton przyciskKolo = new JButton("Kolo");
    /** Przycisk odpowiedzialny za dodanie prostok�ta do rysunku. */
    private JButton przyciskProstokat = new JButton("Prostokat");
    /** Przycisk odpowiedzialny za powi�kszenie figury. */
    private JButton przyciskPowieksz = new JButton("+");
    /** Przycisk odpowiedzialny za pomniejszenie figury. */
    private JButton przyciskPomniejsz = new JButton("-");

	/** Przycisk odpowiedzialny za zmian� aktualnego koloru rysowania na czarny. */
	private JButton przyciskBlack = new JButton("");
	/** Przycisk odpowiedzialny za zmian� aktualnego koloru rysowania na czerwony. */
    private JButton przyciskRed = new JButton("");
    /** Przycisk odpowiedzialny za zmian� aktualnego koloru rysowania na zielony. */
    private JButton przyciskGreen = new JButton("");
    /** Przycisk odpowiedzialny za zmian� aktualnego koloru rysowania na niebieski. */
    private JButton przyciskBlue = new JButton("");
    /** Przycisk odpowiedzialny za zmian� aktualnego koloru rysowania na ��ty. */
    private JButton przyciskYellow = new JButton("");

	/** Etykieta dla panelu z przyciskami dodaj�cymi figury. */
    private JLabel etykietaFigury = new JLabel("Figury:");
    /** Etykieta dla panelu z przyciskami odpowiedzialnymi za zmian� stylu rysowania. */
    private JLabel etykietaStyl = new JLabel("Styl:");
    /** Etykieta dla panelu z przyciskami wyboru koloru. */
    private JLabel etykietaKolor = new JLabel("Kolor:");
    /** Etykieta dla panelu z przyciskami sklaowania. */
    private JLabel etykietaSkala = new JLabel("Skala:");

	/** G��wny panel */
    private JPanel panel = new JPanel();

	/** Menu g�rne - tablica pozycji g��wnych */
    private JMenu[] menu = { new JMenu("Plik"),
    						 new JMenu("Figury"),
                             new JMenu("Edytuj"),
                             new JMenu("Pomoc")};
    /** Menu g�rne - tablica podpozycji */
    private JMenuItem[] items = { new JMenuItem("Nowy"),
    							  new JMenuItem("Zapisz"),
    							  new JMenuItem("Otworz"),
    							  new JMenuItem("Zamknij"),
    							  new JMenuItem("Punkt"),
                                  new JMenuItem("Trojkat"),
                                  new JMenuItem("Kolo"),
                                  new JMenuItem("Prostokat"),
                                  new JMenuItem("Wypisz wszystkie"),
                                  new JMenuItem("Przesun w gore"),
                                  new JMenuItem("Przesun w dol"),
                                  new JMenuItem("Przesun w lewo"),
                                  new JMenuItem("Przesun w prawo"),
                                  new JMenuItem("Powieksz"),
                                  new JMenuItem("Pomniejsz"),
                                  new JMenuItem("Opis programu"),
                                  new JMenuItem("Autor") };

	/** Tablica styli rysowania */
	private String[] style = {"Krawedz", "Wypelnienie"};
	/** Rozwijana lista wyboru stylu rysowania. */
	private JComboBox wybierzStyl = new JComboBox(style);

	/**
	 * Konstruktor domniemany.
	 *
	 * Odpowiedzialny za stworzenie obiektu klasy JFrame i dodanie niezb�dnych element�w do interfejsu oraz za inicjalizacj� obiektu klasy <code>Rysunek</code>.
	 */
    public EdytorGraficzny(){
      	super ("Edytor graficzny");
      	setSize(850,600);
      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Pozycje menu
      	for (int i = 0; i < items.length; i++)
			items[i].addActionListener(this);

     	menu[0].add(items[0]);
     	menu[0].add(items[1]);
     	menu[0].add(items[2]);
     	menu[0].addSeparator();
      	menu[0].add(items[3]);

      	menu[1].add(items[4]);
      	menu[1].add(items[5]);
      	menu[1].add(items[6]);
      	menu[1].add(items[7]);
     	menu[1].addSeparator();
      	menu[1].add(items[8]);

      	menu[2].add(items[9]);
      	menu[2].add(items[10]);
      	menu[2].add(items[11]);
      	menu[2].add(items[12]);
      	menu[2].addSeparator();
		menu[2].add(items[13]);
      	menu[2].add(items[14]);

      	menu[3].add(items[15]);
      	menu[3].add(items[16]);

		//Pasek z menu
      	JMenuBar menubar = new JMenuBar();
      	for (int i = 0; i < menu.length; i++)
      		menubar.add(menu[i]);
      	setJMenuBar(menubar);

		//Panele i przyciski
		panel.setLayout(new BorderLayout(5,5));

		JPanel toolbar = new JPanel();
		toolbar.setLayout(new BorderLayout(5,5));

		przyciskPunkt.addActionListener(this);
      	przyciskTrojkat.addActionListener(this);
      	przyciskKolo.addActionListener(this);
      	przyciskProstokat.addActionListener(this);
      	przyciskBlack.addActionListener(this);
      	przyciskRed.addActionListener(this);
      	przyciskGreen.addActionListener(this);
      	przyciskBlue.addActionListener(this);
      	przyciskYellow.addActionListener(this);
      	przyciskPowieksz.addActionListener(this);
      	przyciskPomniejsz.addActionListener(this);

      	JPanel figury = new JPanel();
      	figury.add(etykietaFigury);
		figury.add(przyciskPunkt);
		figury.add(przyciskTrojkat);
		figury.add(przyciskKolo);
		figury.add(przyciskProstokat);
		toolbar.add(figury, BorderLayout.WEST);

		JPanel styl = new JPanel();
		styl.add(etykietaStyl);
		styl.add(wybierzStyl);
		styl.add(etykietaKolor);
		przyciskBlack.setBackground(Color.BLACK);
		przyciskBlack.setPreferredSize(new Dimension(25, 25));
		styl.add(przyciskBlack);
		przyciskRed.setBackground(Color.RED);
		przyciskRed.setPreferredSize(new Dimension(25, 25));
		styl.add(przyciskRed);
		przyciskGreen.setBackground(Color.GREEN);
		przyciskGreen.setPreferredSize(new Dimension(25, 25));
		styl.add(przyciskGreen);
		przyciskBlue.setBackground(Color.BLUE);
		przyciskBlue.setPreferredSize(new Dimension(25, 25));
		styl.add(przyciskBlue);
		przyciskYellow.setBackground(Color.YELLOW);
		przyciskYellow.setPreferredSize(new Dimension(25, 25));
		styl.add(przyciskYellow);
		toolbar.add(styl, BorderLayout.CENTER);

		JPanel skala = new JPanel();
		skala.add(etykietaSkala);
		skala.add(przyciskPowieksz);
		skala.add(przyciskPomniejsz);
		toolbar.add(skala, BorderLayout.EAST);
		panel.add(toolbar, BorderLayout.NORTH);

		//Inicjalizacja rysunku
   		rysunek = new Rysunek();
      	rysunek.addKeyListener(rysunek);
      	rysunek.setFocusable(true);
      	rysunek.setBackground(Color.WHITE);
      	rysunek.addMouseListener(rysunek);
      	rysunek.addMouseMotionListener(rysunek);

		panel.addKeyListener(rysunek);
		panel.setFocusable(true);
      	panel.add(rysunek, BorderLayout.CENTER);
      	setContentPane(panel);

      	setResizable(false);
      	setVisible(true);
    }

	/**
	 * Metoda odpowiada za obs�ug� akcji podj�tych przez u�ytkownika w GUI.
	 *
	 * @param evt zdarzenie wy�apane przez s�uchacza <code>ActionListener</code>
	 */
    public void actionPerformed(ActionEvent evt){
    	Object zrodlo = evt.getSource();
    	rysunek.ustawStyl(wybierzStyl.getSelectedIndex());

		//Zamiana koloru
      	if(zrodlo == przyciskBlack) rysunek.ustawKolor(Color.BLACK);
      	if(zrodlo == przyciskRed) rysunek.ustawKolor(Color.RED);
      	if(zrodlo == przyciskGreen) rysunek.ustawKolor(Color.GREEN);
      	if(zrodlo == przyciskBlue) rysunek.ustawKolor(Color.BLUE);
      	if(zrodlo == przyciskYellow) rysunek.ustawKolor(Color.YELLOW);
		//Plik
		if(zrodlo == items[0]) rysunek.czysc();
		if(zrodlo == items[1]) rysunek.zapisz();
		if(zrodlo == items[2]){
			panel.remove(rysunek);
			rysunek = rysunek.wczytaj();
   			panel.add(rysunek, BorderLayout.CENTER);
		}
		if(zrodlo == items[3]) System.exit(0);
		//Figura
      	if(zrodlo == items[4] || zrodlo == przyciskPunkt) rysunek.dodajPunkt();
      	if(zrodlo == items[5] || zrodlo == przyciskTrojkat) rysunek.dodajTrojkat();
      	if(zrodlo == items[6] || zrodlo == przyciskKolo) rysunek.dodajKolo();
      	if(zrodlo == items[7]|| zrodlo == przyciskProstokat) rysunek.dodajProstokat();
      	if(zrodlo == items[8]) JOptionPane.showMessageDialog(null, rysunek.toString(), "Zestawienie figur", JOptionPane.INFORMATION_MESSAGE);
		//Edycja
      	if(zrodlo == items[9]) rysunek.przesun(0, -10);
      	if(zrodlo == items[10]) rysunek.przesun(0, 10);
      	if(zrodlo == items[11]) rysunek.przesun(-10, 0);
      	if(zrodlo == items[12]) rysunek.przesun(10, 0);
      	if(zrodlo == items[13] || zrodlo == przyciskPowieksz) rysunek.skaluj((float)1.1);
      	if(zrodlo == items[14] || zrodlo == przyciskPomniejsz) rysunek.skaluj((float)0.9);
      	//Pomoc
      	if(zrodlo == items[15]){
			String msg="";
			msg += "Opis programu\n\n";
			msg += "Sterowanie klawiatur�:\n";
			msg += "    strza�ki => przesuwanie figur\n";
			msg += "    SHIFT + strza�ki => szybkie przesuwanie figur\n";
			msg += "    +,- => powi�kszanie, pomniejszanie\n";
			msg += "    DEL => kasowanie figur\n";
			msg += "    p => dodanie punktu\n";
			msg += "    t => dodanie nowego tr�jk�ta\n";
			msg += "    k => dodanie nowego ko�a\n";
			msg += "    r => dodanie nowego prostok�ta\n";
			msg += "    SHIFT + c,r,g,b,y - zmiana koloru rysowania\n";
			msg += "    n => nowy rysunek\n";
			msg += "    s => zapisz rysunek(plik *.edg)\n";
			msg += "    o => otw�rz rysunek(plik *.edg)\n";
			msg += "    0-9 => zaznaczanie figury o danym numerze\n";
			msg += "    ALT + 0-9 => zmiana zaznaczenia\n\n";
			msg += "Sterowanie myszk�:\n";
			msg += "    lewy przycisk => zaznaczanie figur\n";
			msg += "    ALT + lewy przycisk => zmiana zaznaczenia\n";
			msg += "    przeci�ganie => przesuwanie figur\n";

      		JOptionPane.showMessageDialog(this, msg, "Opis programu", JOptionPane.INFORMATION_MESSAGE);
      	}
      	if(zrodlo == items[16]) JOptionPane.showMessageDialog(this, "Autor: Wojciech Mielczarek", "Autor", JOptionPane.INFORMATION_MESSAGE);

      	rysunek.requestFocus(); //przywrocenie ogniskowania w celu przywrocenia obslugi zadarez� pd klawiatury
      	repaint();
    }

	/**
	 * Metoda g��wna - tworzy nowy obiekt klasy <code>EdytorGraficzny</code> i umo�liwia przetestowanie programu.
	 */
    public static void main(String[] args){
    	EdytorGraficzny edytor = new EdytorGraficzny();
    }
}