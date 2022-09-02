package version_10_20220205;

import java.awt.Image;

//우주선 클래스 (내 유닛)
public class StarShip extends Common {
	//필드
	private GalagonGame game;
	
	//생성자
	public StarShip(GalagonGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = 0;
		dy = 0;
	}
	
	//우주선 움직임 오버라이딩
	@Override
	public void move() {
		if((dx < 0) && (x < 10)) {
			return;
		}
		if((dx > 0) && ((x + getWidth()) > game.getWidth())) {
			return;
		}
		super.move();
	}
	
	//적과 부딪힘 오버라이딩
	@Override
	public void handleCollision(Common other) {
		if(other instanceof Alien) {
			game.endGamemg();
		}
	}
}
