package version_10_20220205;

import java.awt.Image;

//우주선 탄환 클래스
public class Shot extends Common {
	//필드
	private GalagonGame game;
	
	//생성자
	public Shot(GalagonGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dy = -3;
	}
	
	//우주선 탄환 움직임 오버라이딩
	@Override
	public void move() {
		super.move();
		if(y < -100) {
			game.removeSprite(this);
		}
	}
	
	//적과 부딪힘 오버라이딩
	@Override
	public void handleCollision(Common other) {
		if(other instanceof Alien) {
			Sound ts = new Sound("boom6.wav", true, 1);
			ts.start();
			
			game.removeSprite(this);
			game.removeSprite(other);
		}
	}
}
