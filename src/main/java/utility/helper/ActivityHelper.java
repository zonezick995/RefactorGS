package utility.helper;

import utility.PropertiesUtils;

public class ActivityHelper {
    // Seller
    public final static String sellerBundleId = PropertiesUtils.getSellerBundleId();
    public final static String sellerLoginActivity = "com.mediastep.gosellseller.modules.credentials.login.LoginActivity";
    public final static String sellerHomeActivity = "com.mediastep.gosellseller.modules.tabs.main.MainActivity";
    public final static String sellerCreateProductActivity = "com.mediastep.gosellseller.modules.upload_product.CreateProductActivity";
    public final static String sellerProductMgmtActivity = "com.mediastep.gosellseller.modules.product_management.ProductManagementActivity";
    public final static String sellerProductDetailActivity = "com.mediastep.gosellseller.modules.upload_product.CreateProductActivity";
    public final static String sellerProductBranchInventoryActivity = "com.mediastep.gosellseller.modules.upload_product.inventory";

    // Buyer
    public final static String buyerBundleId = PropertiesUtils.getBuyerBundleId();
    public final static String buyerSplashActivity = "%s.ui.modules.splash.SplashScreenActivity".formatted(buyerBundleId);
    public final static String buyerHomeActivity = "com.mediastep.gosell.ui.MainActivity";
}
