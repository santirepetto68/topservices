package topModels;

import java.util.ArrayList;


public class Inventory {

		private Integer type;
		private Integer size;
		private Integer maxSize;
		private Integer no;
		private Integer crc;
		private ArrayList<Items> items;
		
		public Inventory(){
			super();
		}
		
		public Inventory(Integer type, Integer size, Integer maxSize, Integer no, Integer crc, ArrayList<Items> items){
			super();
			this.type = type;
			this.size = size;
			this.maxSize = maxSize;
			this.no = no;
			this.crc = crc;
			this.items = items;
		}
		
		
		public Integer getType(){
			return type;
		}
		
		public void setType(Integer type){
			this.type = type;
		}
		
		public Integer getSize(){
			return size;
		}
		public void setSize(Integer size){
			this.size = size;
		}
		
		public Integer getMaxSize(){
			return maxSize;
		}
		
		public void setMaxSize(Integer maxSize){
			this.maxSize = maxSize;
		}
		
		public Integer getNo(){
			return no;
		}
		public void setNo(Integer no){
			this.no = no;
		}
		
		public Integer getCrc(){
			return crc;
		}
		
		public void setCrc(Integer crc){
			this.crc = crc;
		}
		
		public ArrayList<Items> getItems(){
			return items;
		}
		
		public void setItems(ArrayList<Items> items){
			this.items = items;
		}
		
}
