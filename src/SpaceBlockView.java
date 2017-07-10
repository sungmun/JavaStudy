
public class SpaceBlockView {
	public void printrealtimemap(Space[][] realtimemap){
		int height=TetrisControlManager.getHeight();
		int width=TetrisControlManager.getWidth();
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
			
			}
		}
	}
	private String[] firstCellFirstRow(String space) {
		// TODO Auto-generated method stub
		String[] space1={"¦£ ¦¡ ¦¤","¦¢ "+space+" ¦¢","¦¦ ¦¡ ¦¥"};
		return space1;
	}
	private String[] firstCellRow(String space) {
		// TODO Auto-generated method stub
		String[] space1={" ¦¡ ¦¤",space+" ¦¢"," ¦¡ ¦¥"};
		return space1;
	}
	private String[] cellFirstRow(String space) {
		// TODO Auto-generated method stub
		String[] space1={"¦¢ "+space+" ¦¢","¦¦ ¦¡ ¦¥"};
		return space1;
	}
	private String[] cellRow(String space) {
		// TODO Auto-generated method stub
		String[] space1={" "+space+" ¦¢"," ¦¡ ¦¥"};
		return space1;
	}
}
