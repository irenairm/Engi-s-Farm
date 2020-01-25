package product;

public class Product {
	
	String nama;
    long harga;

	public Product(String _nama, long _harga) {
		// TODO Auto-generated constructor stub
		nama = _nama;
        harga  = _harga;
	}
	
	public String getNama(){
        return nama;
    }

    public long getHarga(){
        return harga;
    }

    public void addHarga(long _harga) {
        harga += _harga;
    }

}
