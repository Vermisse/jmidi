package demo;

import javax.sound.midi.*;

/**
 * 演示音乐：D大调卡农
 * 
 * (Sequencer版本音质比Receiver版本好，但是如果系统没开Java预处理权限的话前几小节会抢拍)
 * 
 * @author vermisse
 */
public class CanonTest {
    
    public static void main(String[] args) throws Exception {
        int volume = 60;
        
		for (int i = 0; i < MELODY.length; i++) {
			volume = volume < 100 ? volume + 2 : volume;
			
			play(MELODY[i], volume, 1, i * 2);
			play(CHORD[i], volume, 2, i * 2);
		}

		for(int i = 0; i < LAST.length * 2 * 8; i++) {
			byte[] note = LAST[i / (2 * 8)];
			play(key(note[0], note[1]), volume + (i % (2 * 8) == 0 ? 27 : 10), MELODY.length * 2 + i);

			if(i % 2 == 0)
				play(CHORD[i / 2 + MELODY.length], volume, 2, MELODY.length * 2 + i);
		}

		for(byte[] end : END) {
			play(key(end[0], end[1]), 95, MELODY.length * 2 + LAST.length * 2 * 8);
			track.add(event(ShortMessage.NOTE_OFF, 1, key(end[0], end[1]), 0, MELODY.length * 2 + LAST.length * 2 * 8 + 32));
		}
        
        player.setSequence(seq);
        player.start();
    }
	
    static MidiEvent event(int comd, int chan, int key, int volume, int time) throws Exception {
        ShortMessage msg = new ShortMessage();
		msg.setMessage(comd, chan, key, volume);
		return new MidiEvent(msg, time);
    }

	static Sequencer player;
	static Track track;
	static Sequence seq;
	static { try { player = MidiSystem.getSequencer(); player.open(); seq = new Sequence(Sequence.PPQ, 4); track = seq.createTrack(); player.setTempoInBPM(154); player.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); } catch (Exception e) {} }

	static final byte CENTER = 62;
	static final byte[][] MELODY = {{0,3},{},{},{},{},{},{},{},{-1,7},{},{},{},{},{},{},{},{0,1},{},{},{},{},{},{},{},{-1,7,-1,5},{},{},{},{},{},{},{},{-1,6,-1,4},{},{},{},{},{},{},{},{0,1,-1,3},{},{},{},{},{},{},{},{-1,6,-1,4},{},{},{},{},{},{},{},{-1,7,-1,5},{},{},{},{},{},{},{},{1,3,1,1,0,3},{},{},{},{},{},{},{},{1,2,0,7,0,5},{},{},{},{0,4},{},{},{},{1,1,0,6,0,3},{},{},{},{},{},{},{},{0,7,0,5,0,3},{},{},{},{0,2},{},{},{},{0,6,0,4,0,1},{},{},{},{},{},{},{},{0,5,0,3,0,1},{},{},{},{0,3},{},{},{},{0,6,0,4,0,2},{},{},{},{},{},{},{},{0,7,0,5,0,2},{},{},{},{0,4},{},{},{},{1,1,0,3},{},{},{},{},{},{},{},{0,7,0,5},{},{},{},{0,4},{},{},{},{1,1,0,3},{},{},{},{1,3,0,6},{},{},{},{1,5,1,3},{},{},{},{1,6,1,2},{},{},{},{1,4,0,6},{},{},{},{1,1},{},{},{},{1,3,0,5},{},{},{},{0,5},{},{},{},{0,4,0,2},{},{},{},{1,1,0,6},{},{},{},{1,1,0,5},{},{},{},{1,1},{},{0,7},{},{1,1,0,3},{},{0,7},{},{1,1},{},{0,3},{},{0,5,-1,7},{},{},{},{0,7,0,4},{},{},{},{1,1,0,3},{},{},{},{1,3,0,6},{},{},{},{1,5,0,7},{},{1,3},{},{1,5,1,2},{},{1,6},{},{1,4,0,6},{},{1,3},{},{1,2},{},{1,4},{},{1,3,0,5},{},{1,2},{},{1,1},{},{0,7},{},{0,6,0,4},{},{0,4,0,2},{},{1,1,0,6},{},{},{},{1,1,0,5},{},{0,4},{},{1,1,0,3},{},{0,7,0,2},{},{1,1,0,3},{},{0,7},{},{1,1},{},{0,4},{},{0,5,0,1},{},{-1,7},{},{0,7,0,5},{},{0,4},{},{1,1,0,3},{},{},{},{1,3,0,6},{},{1,1},{},{1,5,0,7},{},{1,3},{},{1,5,1,2},{},{1,6},{},{1,4,1,1,0,6},{},{1,3},{},{1,2,0,7},{},{1,4},{},{1,3,1,1,0,5},{},{1,2,0,7},{},{1,1,0,6},{},{0,7,0,5},{},{0,6,0,4},{},{0,5,0,3},{},{0,4,0,2},{},{1,1},{},{1,1,0,5},{},{},{0,2},{0,7},{},{1,2},{0,5},{1,3},{},{},{0,5},{1,3,0,5},{},{1,1,0,5},{1,2,0,5},{},{},{},{1,3,0,5},{1,4,0,5},{1,3},{1,2},{0,3},{1,1},{},{},{1,1,0,3},{},{},{0,7,0,3},{1,1,0,3},{0,7,0,3},{},{0,5},{0,3},{},{0,5},{},{0,1},{0,6,0,4},{0,1},{},{0,7,0,5},{},{0,1},{1,1},{0,1},{0,5},{},{},{},{},{},{},{0,1},{0,6,0,4},{0,1},{},{0,1},{0,4},{0,6},{1,1},{0,2},{0,7},{0,2},{},{1,1,0,2},{},{0,2},{1,2},{0,5},{1,3},{},{},{},{1,3,0,5},{1,2,0,5},{1,1,0,5},{1,2,0,5},{},{0,5},{},{1,3,0,5},{1,4,0,5},{1,3},{1,2},{0,3},{1,1},{},{},{1,1,0,3},{},{},{0,7,0,3},{1,1,0,3},{0,7,0,3},{},{0,5},{0,3},{},{0,5},{},{0,1},{0,6,0,4},{0,1},{},{1,1},{},{0,6},{1,4},{0,5},{1,3},{0,5},{1,2},{1,1},{},{},{},{0,1},{0,6,0,4},{0,1},{},{0,1},{0,4},{0,6},{1,1},{0,2},{0,7},{0,2},{},{1,1,0,2},{},{0,2},{1,2},{1,1},{1,5},{},{1,3},{1,4},{1,5},{},{1,3},{1,4},{1,5},{0,5},{0,6},{0,7},{1,1},{1,2},{1,3},{1,4},{1,3},{},{1,1},{1,2},{1,3},{},{0,3},{0,4},{0,5},{0,6},{0,5},{0,4},{0,5},{1,1},{0,7},{1,1},{0,6},{},{1,1},{0,7},{0,6},{},{0,5},{0,4},{0,5},{0,4},{0,3},{0,4},{0,5},{0,6},{0,7},{1,1},{0,6},{},{1,1},{0,7},{1,1},{},{0,7},{1,1},{0,7},{0,6},{0,7},{1,1},{1,2},{1,3,1,1},{1,4,1,2},{1,5,1,3},{1,5,1,3},{},{1,3,1,1},{1,4,1,2},{1,5,1,3},{},{1,3,1,1},{1,4,1,2},{1,5,1,3},{0,5,0,7},{0,6},{0,7},{1,1},{1,2},{1,3},{1,4},{1,3,1,1},{},{1,1,0,6},{1,2,0,7},{1,3,1,1},{},{0,3},{0,4},{0,5},{0,6},{0,5},{0,4},{0,5},{1,1},{0,7},{1,1},{0,6,0,4},{},{1,1,0,6},{0,7,0,5},{0,6,0,4},{},{0,5,0,3},{0,4,0,2},{0,5,0,3},{0,4,0,2},{0,3,0,1},{0,4,0,2},{0,5,0,3},{0,6,0,4},{0,7,0,5},{1,1,0,6},{0,6,0,4},{},{1,1,0,6},{0,7,0,5},{1,1,0,6},{},{0,7,0,5},{1,1,0,6},{0,7,0,5},{0,6,0,4},{0,7,0,5},{1,1,0,6},{1,2,0,7},{1,3,1,1},{1,4,1,2},{1,5,1,3},{1,3},{},{1,1},{1,2},{1,3},{},{1,2},{1,1},{1,2},{0,7},{1,1},{1,2},{1,3},{1,2},{1,1},{0,7},{1,1},{},{0,6},{0,7},{1,1},{},{0,1},{0,2},{0,3},{0,4},{0,3},{0,2},{0,3},{1,1},{0,7},{1,1},{0,6},{},{1,1},{0,7},{0,6},{},{0,5},{0,4},{0,5},{0,4},{0,3},{0,4},{0,5},{0,6},{0,7},{1,1},{0,6},{},{1,1},{0,7},{1,1},{},{0,7},{0,6},{0,7},{1,1},{1,2},{1,1},{0,7},{1,1},{0,6},{0,7},{1,1},{},{-1,5},{},{0,1},{},{0,3},{},{},{},{-1,2},{},{-1,5},{},{-1,7},{},{},{},{-1,3},{},{-1,6},{},{0,1},{},{},{},{-1,5},{},{-1,7},{},{0,3},{},{},{},{-1,1},{},{-1,4},{},{-1,6},{},{},{},{-1,3},{},{-1,5},{},{0,1},{},{},{},{-1,2},{},{-1,6},{},{0,1},{},{},{},{-1,2},{},{-1,5},{},{-1,7},{},{},{},{1,3,1,1},{1,4,1,2},{1,5,1,3},{},{1,3,1,1},{},{},{},{0,7,0,5},{1,1,0,6},{1,2,0,7},{},{0,7,0,5},{},{},{0,6},{1,1,0,6},{1,2,0,7},{1,3,1,1},{},{1,1},{},{},{0,6,1,1},{1,3,0,7},{1,2,0,6},{1,1,0,6},{},{0,7,0,5},{},{},{0,4},{0,6,0,4},{0,7,0,5},{1,1,0,6},{},{0,6,0,4},{},{},{0,3},{0,5,0,3},{0,6,0,4},{1,1,0,4},{},{0,5,0,3},{},{},{0,6,0,4},{0,7,0,5},{0,7,0,5},{1,1,0,6},{},{0,6,0,4},{0,5},{},{0,5},{0,7,0,5},{1,1,0,6},{1,2,0,7},{},{0,7,0,5},{},{},{0,5},{1,3,1,1},{1,4,1,2},{1,5,1,3},{},{1,3,1,1},{},{},{},{1,2,0,7},{1,3,1,1},{1,4,1,2},{0,7},{1,2},{0,6},{},{0,6},{1,1,0,6},{1,2,0,7},{1,3,1,1},{},{1,1},{0,7},{},{0,7},{1,5,1,3},{1,4,1,2},{1,3,0,7},{0,7},{1,5,1,3},{1,1},{1,6,1,4},{1,1},{1,6,1,4},{1,5,1,3},{1,4},{},{1,6,1,4},{1,1},{1,5,1,3},{1,1},{1,5,1,3},{1,4},{1,3},{1,1},{1,5,1,3},{1,1},{1,6,1,4},{1,5,1,3},{1,4,1,1},{1,6,1,4},{1,5,1,3},{1,4,1,1},{1,6,1,4},{1,2},{1,7,1,5},{1,6,1,4},{1,2},{0,5},{0,7,0,5},{1,1,0,5},{1,2},{0,5},{1,3},{0,5},{},{0,5},{1,3,0,5},{1,2,0,5},{1,1,0,5},{1,2,0,5},{},{0,5},{},{1,3,0,5},{1,4,0,5},{1,3},{1,2},{0,3},{1,1,0,3},{},{},{1,1,0,3},{},{},{0,7,0,3},{1,1,0,3},{0,7,0,3},{},{0,5},{0,3},{},{0,5},{},{0,1},{0,6,0,4},{0,1},{},{0,7,0,5},{},{},{1,1},{0,1},{0,5},{},{},{1,1,0,5},{},{1,1,0,5},{1,1,0,5},{1,1,0,5},{0,6,0,4},{0,1},{},{0,1},{0,4},{0,6},{1,1},{0,2},{0,7},{0,2},{},{1,1,0,2},{},{0,2},{1,2},{0,5},{1,3},{0,5},{},{0,5},{1,3,0,5},{1,2,0,5},{1,1,0,5},{1,2,0,5},{},{0,5},{},{1,3,0,5},{1,4,0,5},{1,3},{1,2},{0,3},{1,2},{},{},{1,1},{},{},{0,7},{1,1},{0,3},{0,5},{0,7},{1,1},{1,3},{1,5},{1,7},{2,1},{1,7},{1,6},{1,5},{1,4},{1,5},{1,4},{1,3},{1,2},{1,3},{1,2},{1,1},{0,7},{1,1},{0,7},{0,6},{0,5},{0,6},{0,5},{0,4},{0,5},{0,6},{0,4},{1,1},{0,5},{0,7},{0,6},{0,5},{1,1},{0,5},{0,5},{1,2},{0,5},{1,3},{0,5},{},{0,5},{1,3},{0,5},{1,4},{0,5},{1,5,0,7},{0,5},{1,6,0,6},{0,5},{1,5,1,1},{0,5},{1,4,0,5},{0,3},{1,3,1,1},{0,3},{},{0,3},{1,1,0,3},{1,1,0,3},{1,2},{0,3},{1,3,0,5},{0,4},{1,4},{0,3},{1,3},{0,3},{1,2},{0,1},{0,6,0,4},{0,1},{},{0,1},{0,6},{0,7},{1,1},{0,5,0,1},{},{1,1},{},{1,1},{1,1,0,5},{1,1,0,5},{},{1,1},{0,6,0,4},{0,1},{},{0,1},{0,4},{0,6},{1,1},{0,2},{1,1},{0,2},{},{0,7,0,2},{},{1,1},{1,2},{0,5},{1,3,1,1},{0,5},{},{0,5},{1,3,1,1},{0,5},{1,4,1,2},{0,5},{1,5,0,7},{0,6},{1,6,1,1},{0,5},{1,5,0,7},{0,5},{1,4},{0,3},{1,3,1,1},{0,3},{},{0,3},{1,1,0,3},{1,1,0,3},{1,2},{0,3},{1,3,0,5},{0,4},{1,4},{0,3},{1,3,0,5},{0,3},{1,2},{0,1},{0,6,0,4},{0,1},{},{0,1},{0,6},{0,7},{1,1},{0,5,0,1},{},{1,1},{0,7},{1,1},{0,5,0,3},{1,1},{0,7},{1,1},{0,6,0,4},{1,1},{0,7},{1,1},{0,6,0,4},{1,1},{0,7},{1,1},{0,7,0,2},{0,5},{0,2},{1,1,0,2},{},{0,2},{1,2,0,7},{0,5},};
	static final byte[][] CHORD = {{-1,1,-1,5},{},{},{},{},{},{},{},{-2,5,-1,2},{},{},{},{},{},{},{},{-2,6,-1,3},{},{},{},{},{},{},{},{-2,3,-2,7},{},{},{},{},{},{},{},{-2,4,-1,1},{},{},{},{},{},{},{},{-2,1,-2,5},{},{},{},{},{},{},{},{-2,4,-1,1},{},{},{},{},{},{},{},{-2,5,-1,2},{},{},{},{},{},{},{},{-1,1,-1,5,0,1},{},{},{},{},{},{},{},{-2,5,-1,2,-1,5},{},{},{},{},{},{},{},{-2,6,-1,3,-1,6},{},{},{},{},{},{},{},{-2,3,-2,7,-1,3},{},{},{},{},{},{},{},{-2,4,-1,1,-1,4},{},{},{},{},{},{},{},{-2,1,-2,5,-1,1},{},{},{},{},{},{},{},{-2,4,-1,1,-1,4},{},{},{},{},{},{},{},{-2,5,-1,2,-1,5},{},{},{},{},{},{},{},{-1,1,-1,5,0,1},{},{},{},{},{},{},{},{-2,5,-1,2,-1,5},{},{},{},{},{},{},{},{-2,6,-1,3,-1,6},{},{},{},{},{},{},{},{-2,3,-2,7,-1,3},{},{},{},{},{},{},{},{-2,4,-1,1,-1,4},{},{},{},{},{},{},{},{-2,1,-2,5,-1,1},{},{},{},{},{},{},{},{-2,4,-1,1,-1,4},{},{},{},{},{},{},{},{-2,5,-1,2,-1,5},{},{},{},{},{},{},{},{-1,1},{},{-1,5},{},{0,1},{},{},{},{-2,5},{},{-1,2},{},{-1,5},{},{},{},{-2,6},{},{-1,3},{},{0,1},{},{},{},{-2,3},{},{-2,7},{},{-1,5},{},{},{},{-2,4},{},{-1,1},{},{-1,6},{},{},{},{-1,1},{},{-1,5},{},{0,1},{},{},{},{-2,4},{},{-1,1},{},{-1,6},{},{},{},{-2,5},{},{-1,2},{},{-1,7},{},{},{},{-1,1},{},{-1,5},{},{0,1},{},{},{},{-2,5},{},{-1,2},{},{-1,5},{},{},{},{-2,6},{},{-1,3},{},{0,1},{},{-1,3},{},{-2,3},{},{-2,7},{},{-1,5},{},{-2,7},{},{-2,4},{},{-1,1},{},{-1,6},{},{-1,1},{},{-1,1},{},{-1,5},{},{0,3},{},{-1,5},{},{-2,4},{},{-1,1},{},{-1,6},{},{-1,1},{},{-2,5},{},{-1,2},{},{-1,7},{},{-1,2},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4},{},{-1,4},{},{-1,6},{},{0,1},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4},{},{-1,4},{},{-1,6},{},{0,1},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5},{},{-1,5},{},{-1,7},{},{0,2},{},{-2,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-2,3},{},{-1,3},{},{-1,5},{},{-1,7},{},{-2,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5},{},{-1,5},{},{-1,7},{},{0,2},{},{-2,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-2,3},{},{-1,3},{},{-1,5},{},{-1,7},{},{-2,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5},{},{-1,5},{},{-1,7},{},{0,2},{},{-2,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-2,3},{},{-1,3},{},{-1,5},{},{-1,7},{},{-2,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{},{},{},{},{},{},{-2,5,-3,5},{},{},{},{},{},{},{},{-2,6,-3,6},{},{},{},{},{},{},{},{-2,3,-3,3},{},{},{},{},{},{},{},{-2,4,-3,4},{},{},{},{},{},{},{},{-1,1,-2,1},{},{},{},{},{},{},{},{-2,4,-3,4},{},{},{},{},{},{},{},{-2,5,-3,5},{},{},{},{},{},{},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-1,1,-2,1},{},{-1,5},{},{0,1},{},{0,3},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},{-2,6,-3,6},{},{-1,3},{},{-1,6},{},{0,1},{},{-1,3,-2,3},{},{-1,5},{},{-1,7},{},{0,3},{},{-2,4,-3,4},{},{-1,1},{},{-1,4},{},{-1,6},{},{-1,1,-2,1},{},{-1,3},{},{-1,5},{},{0,1},{},{-2,4,-3,4},{},{-1,2},{},{-1,6},{},{0,1},{},{-2,5,-3,5},{},{-1,2},{},{-1,5},{},{-1,7},{},};
	static final byte[][] LAST = {{1,3},{1,2},{1,1},{0,7},{0,6},{0,5},{0,6},{0,7},{1,3},{1,2},{1,1},{0,7},{0,6},{0,5},{0,6},{0,7},};
	static final byte[][] END = {{1,1},{0,3},{-1,1},{-1,5}};

	static void play(byte key, int volume, int pos) throws Exception {
		track.add(event(ShortMessage.NOTE_OFF, 1, key, volume, pos));
		track.add(event(ShortMessage.NOTE_ON, 1, key, volume, pos));
	}

	static void play(byte[] note, int volume, int type, int pos) throws Exception {
		for(byte i = 0; i < note.length; i += 2)
			play(key(note[i], note[i + 1]), type == 1 ? volume + (i == 0 ? 27 : 10) : volume, pos);
	}

	static byte key(byte area, byte note) {
		byte result = CENTER - 1;
		result += 12 * area;
		for (byte i = 0; i < note; i++) {
			switch (i % 7 + 1) {
			case 1:
			case 4:
				result++;
				break;
			default:
				result += 2;
			}
		}
		return result;
	}
}