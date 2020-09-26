package homeWork8;

public class Train implements Comparable<Train>{


	private float number;
	private String type;
	private String start;
	private Int dest;
	private double price;
	//branchtest
	
	public Train(){}
	
	public Train(int num, String type, String start, String dest, double price){
		
		setNumber(num);
		setType(type);
		setStart(start);
		setDest(dest);
		setPrice(price);
		
	}
	
	//setter
	public void setNumber(int number) {
		if(number != 0) {
			this.number = number;
		}else {
			System.out.println("請重新輸入班次");
		}
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	public void setPrice(double price) {
		if(price <= 0) {
			System.out.println("票價不得為0或負數");
		}else {
			this.price = price;
		}
	}
	//getter
	public int getNumber() {
		return number;
	}
	
	public String getType() {
		return type;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getDest() {
		return dest;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int compareTo(Train aTrain) {
		if (this.number < aTrain.number) {
			return 1;
		}else if (this.number == aTrain.number) {
			return 0;
		}else {
			return -1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + number;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		if (number != other.number)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
