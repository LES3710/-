package version_10_20220205;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//공통 클래스
public class Common {
	//필드
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected Image image;
	
	//생성자
	public Common(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	//이미지 가로폭 getter
	public int getWidth() {
		return image.getWidth(null);
	}
	
	//이미지 세로폭 getter
	public int getHeight() {
		return image.getHeight(null);
	}
	
	//이미지 그리기 (화면에 출력)
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	//이미지 움직임
	public void move() {
		x += dx;
		y += dy;
	}
	
	//이미지 x, y 축 이동량 setter
	public void setDx(int dx) {
		this.dx = dx;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	//이미지 x, y 축 이동량 getter
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
	
	//이미지 x, y 축 좌표 getter
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	//충돌 확인 : 나와 other 객체가 부딪혔는지 확인
	public boolean checkCollision(Common other) {
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		myRect.setBounds(x, y, getWidth(), getHeight());
		otherRect.setBounds(other.x, other.y, other.getWidth(), other.getHeight());
		
		return myRect.intersects(otherRect);
	}
	
	//나와 other 객체가 충돌 시의 동작
	public void handleCollision(Common other) {}
}
