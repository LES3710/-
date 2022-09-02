package version_10_20220205;

import java.awt.Image;

//적 클래스
public class Alien extends Common {
	//필드
	private GalagonGame game;
	private int count = 0;
	
	//생성자
	public Alien(GalagonGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = 1;
		dy = 3;
	}
	
	//적 움직임 오버라이딩
	@Override
	public void move() {
		if(count > 100) {
			dx = -dx;
			count = 0;
		}
		if(y > 350 || y < 0) {
			dy = -dy;
		}
		int attack = (int)(Math.random() * 3000)+1;
		if(attack == 7) {
			game.Attack();
		}
		super.move();
		count++;
	}
}
