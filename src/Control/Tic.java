package Control;

import java.io.FileInputStream;

import javax.swing.Timer;

import javazoom.jl.player.Player;

@SuppressWarnings("serial")
public abstract class Tic extends Timer {
	String str;
	Thread sound;
	Player playMp3;

	public Tic(int delay, TicAction listener) {
		super(delay, listener);
	}

	@Override
	public void stop() {
		super.stop();
		timerstop();
		playMp3.close();
		str = "";
		sound.interrupt();
	}

	@Override
	public void start() {
		super.start();
		try {
			str = "Tetris_BGM.mp3";
			sound = new Thread(new Runnable() {

				public void run() {
					try {
//						while (true) {
//							FileInputStream fls = new FileInputStream(str);
//							playMp3 = new Player(fls);
//							playMp3.play();
//							fls.close();
//						}
					} catch (Exception e) {
					}
				}

			});
			sound.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract public void timerstop();
}
