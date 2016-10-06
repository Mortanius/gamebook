package beans;

@SuppressWarnings("serial")
public class EquipItem extends Item{

	boolean equipado;
	
	public EquipItem(){
		this.equipado = false;
		
	}

	public boolean isEquipado() {
		return equipado;
	}

	public void setEquipado(boolean equipado) {
		this.equipado = equipado;
	}
	
	public boolean equipar(){
		this.setEquipado(true);
		return equipado;
	}
	
	public boolean desequipar(){
		this.setEquipado(false);
		return equipado;
	}
}
