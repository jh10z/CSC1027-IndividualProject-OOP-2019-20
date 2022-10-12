package part02;
/**
 * This class extends Product to allow access to the protected mutators
 * @author 40259391 | Kar Hay Ho
 *
 */
public class CProduct extends Product{
	/**
	 *Construtor for CProduct, only passes super class parameters
	 */
	public CProduct(String proMake, String proModel, double proPrice, int proQtyAvailable, boolean proDiscounted) throws Exception {
		super(proMake, proModel, proPrice, proQtyAvailable, proDiscounted);
	}
	
	/** 
	 * The mutator for productMake through protected method in product
	 */
	public void setProMake(String proMake) throws Exception {
		super.setProMake(proMake);
	}
	
	/** 
	 * The mutator for productModel through protected method in product
	 */
	public void setProModel(String proModel) throws Exception {
		super.setProModel(proModel);
	}


}
