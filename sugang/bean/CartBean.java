package sugang.bean;

public class CartBean {
	private int cart_id; //장바구니의 아이디
	private String buyer; //구매자
	private int subject_id; //구매된 책의 아이디
	private String subject_title;//구매된 책명
	private int buy_count; //판매수량
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_title() {
		return subject_title;
	}
	public void setSubject_title(String subject_title) {
		this.subject_title = subject_title;
	}
	public int getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(int buy_count) {
		this.buy_count = buy_count;
	}
}
