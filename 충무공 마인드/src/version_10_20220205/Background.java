package version_10_20220205;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

//배경 별 클래스
public class Background {
	//필드
	final int STAR_SIZE = 4;
	final int STAR = 80;
	GalagonGame game;
	Vector<Point> starVector = new Vector<Point>();
	Vector<Point> starVector2 = new Vector<Point>();
	
	//생성자
	public Background(GalagonGame game) {
		this.game = game;
	}
	
	//별 좌표 및 크기 생성
	void addStar() {
		for(int i = 0; i < STAR; i++) {
			Point p = new Point((int)(Math.random()*game.getWidth()), (int)(Math.random()*game.getHeight()));
			starVector.add(p);
			int size = (int) (Math.random() * STAR_SIZE) + 1;
			Point p2 = new Point(size, size);
			starVector2.add(p2);
		}
	}
	
	//별 그리기
	public void draw(Graphics g) {
		for(int i = 0; i < starVector.size(); i++) {
			Point p = starVector.get(i);
			Point p2 = starVector2.get(i);
			if(p.x%2 == 0)g.setColor(Color.WHITE);
			else if(p.x%3 == 0) g.setColor(Color.BLUE);
			else if(p.x%5 == 0) g.setColor(Color.RED);
			else if(p.x%7 == 0) g.setColor(Color.YELLOW);
			else g.setColor(Color.GREEN);
			if(p.x%2 == 0 && p.y > 50 && p.y < 100) g.setColor(Color.BLACK);
//			if(p.x%3 == 0 && p.y > 150 && p.y < 200) g.setColor(Color.BLACK);
			if(p.x%3 == 0 && p.y > 250 && p.y < 300) g.setColor(Color.BLACK);
//			if(p.x%7 == 0 && p.y > 350 && p.y < 400) g.setColor(Color.BLACK);
			if(p.x%5 == 0 && p.y > 450 && p.y < 500) g.setColor(Color.BLACK);
//			if(p.x%3 == 0 && p.y > 550 && p.y < 600) g.setColor(Color.BLACK);
			if(p.x%7 == 0 && p.y > 650 && p.y < 700) g.setColor(Color.BLACK);
//			if(p.x%7 == 0 && p.y > 750 && p.y < 800) g.setColor(Color.BLACK);
			g.fillOval(p.x, p.y, p2.x, p2.y);
		}
	}
	
	//별 움직임
	public void move() {
		int offsetY = 2;
		for(int i = 0; i < STAR; i++) {
			Point p = starVector.get(i);
			p.y += offsetY;
			if(p.y > game.getHeight()) {
				p.x = (int)(Math.random()*game.getWidth());
				p.y = 5;
			}
		}
		
	}
}
