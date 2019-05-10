package topServices;

import java.util.ArrayList;

import topModels.Inventory;
import topModels.Items;

public class CharParser {

	public static Object parseIds(String payload) {

		String chaIds = payload;
		String[] output = chaIds.split("\\;");
		return output;
	}

	public static String[] parseInv(String payload) {
		String[] inventory = payload.split("@");
		String maxSize = inventory[0];
		String[] inventory2 = inventory[1].split("#");
		String no = inventory2[0];
		String invString = inventory2[1];
		String[] response = { maxSize, no, invString };

		return response;
	}

	public static Object getInventory(Integer maxSize, Integer no, String payload) {

		String[] output = payload.split("\\;");

		Inventory Inventory = new Inventory();
		Inventory.setType(Integer.valueOf(output[0]));
		Inventory.setSize(Integer.valueOf(output[1]));
		Inventory.setNo(no);
		Inventory.setMaxSize(maxSize);

		Integer itemCant = output.length - 3;
		ArrayList<Items> Items = new ArrayList<Items>();
		for (int i = 2; i < itemCant + 2; i++) {
			String[] output2 = output[i].split("\\,");
			Items Item = new Items();
			Item.setItemIndex(i-2);
			Item.setItemId(Integer.valueOf(output2[1]));
			Item.setItemAmount(Integer.valueOf(output2[2]));
			Item.setItemDur1(Integer.valueOf(output2[3]));
			Item.setItemDur2(Integer.valueOf(output2[4]));
			Item.setItemEne1(Integer.valueOf(output2[5]));
			Item.setItemEne2(Integer.valueOf(output2[6]));
			Item.setCero1(0);
			Item.setCero2(0);
			Item.setCero3(0);
			Item.setCero4(Integer.valueOf(output2[10]));
			if (Item.getCero4() == 1){
				Item.setAttr1(Integer.valueOf(output2[11]));
				Item.setAttr2(Integer.valueOf(output2[12]));
				Item.setAttr3(Integer.valueOf(output2[13]));
				Item.setAttr4(Integer.valueOf(output2[14]));
				Item.setAttr5(Integer.valueOf(output2[15]));
				Item.setAttr6(Integer.valueOf(output2[16]));
				Item.setAttr7(Integer.valueOf(output2[17]));
				Item.setAttr8(Integer.valueOf(output2[18]));
				Item.setAttr9(Integer.valueOf(output2[19]));
				Item.setAttr10(Integer.valueOf(output2[20]));
			}
			Items.add(Item);
		}
		Inventory.setItems(Items);
		try {
			Inventory.setCrc(getCrc(Inventory));
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return Inventory;
	}

	public static String decryptInv(String payload, String crypt_key) {
		Integer keyLen = crypt_key.length();
		Integer payloadLen = payload.length();

		String result = "";
		try {
			for (int i = 0; i < payloadLen; i++) {

				char character = payload.charAt(i);
				if (character != ' ') {
					char character2 = crypt_key.charAt(i % keyLen);
					char character3 = (char) (character - character2);
					result = result + character3;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;

	}
	
	public static String encryptInv(String payload, String crypt_key) {
		Integer keyLen = crypt_key.length();
		Integer payloadLen = payload.length();

		String result = "";
		try {
			for (int i = 0; i < payloadLen; i++) {

				char character = payload.charAt(i);
				if (character != ' ') {
					char character2 = crypt_key.charAt(i % keyLen);
					char character3 = (char) (character + character2);
					result = result + character3;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;

	}

	public static Integer getCrc(Inventory payload) {
		Integer crc = payload.getType();
		Integer itemAttrCount = null;
		for (Items item : payload.getItems()) {
			if (item.getCero4() == 0){
				itemAttrCount = 11;
			}else{
				itemAttrCount = 21;
			}
			
			for (int i = 1; i < itemAttrCount; i++) {
				if (i == 1) {
					crc += item.getItemId();
				} else if (i == 2) {
					crc += item.getItemAmount();
				} else if (i == 3) {
					crc += item.getItemDur1();
				} else if (i == 4) {
					crc += item.getItemDur2();
				} else if (i == 5) {
					crc += item.getItemEne1();
				} else if (i == 6) {
					crc += item.getItemEne2();
				} else if (i == 7) {
					crc += item.getCero1();
				} else if (i == 8) {
					crc += item.getCero2();
				} else if (i == 9) {
					crc += item.getCero3();
				} else if (i == 10) {
					crc += 0;
				} else if (i == 11) {
					crc += item.getAttr1();
				} else if (i == 12) {
					crc += item.getAttr2();
				} else if (i == 13) {
					crc += item.getAttr3();
				} else if (i == 14) {
					crc += item.getAttr4();
				} else if (i == 15) {
					crc += item.getAttr5();
				} else if (i == 16) {
					crc += item.getAttr6();
				} else if (i == 17) {
					crc += item.getAttr7();
				} else if (i == 18) {
					crc += item.getAttr8();
				} else if (i == 19) {
					crc += item.getAttr9();
				} else if (i == 20) {
					crc += item.getAttr10();
				} 
			}
			
		}
		return crc;
	}
	
	public static Inventory addItem(Inventory inventory, Items itemAdded){
		
		ArrayList<Items> Items = new ArrayList<Items>();
		Integer itemIndex = null;
		Integer chance = null;
		Integer chance2 = null;
		Items = inventory.getItems();
		
		if (!Items.isEmpty()){
		
		for (Items item : Items) {
			
			Integer itemCount = item.getItemAmount() + itemAdded.getItemAmount();
			Integer item1 = item.getItemId();
			Integer item2 = itemAdded.getItemId();
			if (item1.equals(item2)){
				if(item.getItemAmount() != 99){
				if(itemCount <= 99){
					
					if(chance2 == null){
						item.setItemAmount(item.getItemAmount() + itemAdded.getItemAmount());
						chance2 = 1;
						chance = 1;
					}
				
				}else
				{
					if(chance2 == null){
					item.setItemAmount(99);
					itemCount = itemCount - 99;
					itemAdded.setItemAmount(itemCount);
					chance2 = 1;
					}
				}
				}
			}
				itemIndex = item.getItemIndex();
			
		}
		chance2 = null;
		if (chance == null){
			itemIndex = itemIndex +1;
			itemAdded.setItemIndex(itemIndex);
			Items.add(itemAdded);
			inventory.setSize(inventory.getSize() + 1);
		}
		
		}else
		{
			itemAdded.setItemIndex(0);
			Items.add(itemAdded);
			inventory.setSize(inventory.getSize() + 1);
		}
		
		inventory.setItems(Items);
		inventory.setCrc(getCrc(inventory));
		
		
		return inventory;
	}
	
	public static Integer[] getRedeemCount(Integer value){
		Integer[] myIntArray = new Integer[value];
        
        return myIntArray;
	}
	
	public static Object removeItemInventory(Inventory inventory, Integer itemId, Integer itemAmount){
		ArrayList<Items> Items = new ArrayList<Items>();
		Items = inventory.getItems();
		Integer itemCount = 0;
		Integer status = 0;
		Integer invItemId = 0;
		Integer invItemAmount = 0;
		try{
			for(Items item : Items){
				if (status != 1){					
					invItemId = item.getItemId();
					invItemAmount = item.getItemAmount();
				if(invItemId.intValue() == itemId.intValue()){	
					if (invItemAmount == itemAmount){
						Items.remove(itemCount.intValue());
						inventory.setSize(inventory.getSize() - 1);
						status = 1;
					}else if(invItemAmount.intValue() > itemAmount.intValue()){
						item.setItemAmount(invItemAmount - itemAmount);
						status = 1;
					}
				}else{
					itemCount += 1;
				}
				
				}

			}
		}catch (Exception e){
			System.out.println("Inventory Modified");
		}
		
		inventory.setItems(Items);
		inventory.setCrc(getCrc(inventory));
		return inventory;
	}

}
