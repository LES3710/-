package version_10_20220205;

import java.awt.Image;

//적 탄환 클래스(적 공격)
public class AlienShot extends Common {
	//필드
	private GalagonGame game;
	
	//생성자
	public AlienShot(GalagonGame game, Image image, int x, int y, int dx) {
		super(image, x, y);
		this.game = game;
		this.dx = (int) dx * (game.stage/ 2);
		dy = (int)((3 * game.stage)/2);
	}
	
	//적 탄환 움직임 오버라이딩
	@Override
	public void move() {
		super.move();
		if(y > game.getWidth() + 100) {
			game.removeSprite(this);
		}
	}
	
	//우주선과 부딪힘 오버라이딩
	@Override
	public void handleCollision(Common other) {
		if(other instanceof StarShip) {
			game.endGamemg();
		}
	}
}
