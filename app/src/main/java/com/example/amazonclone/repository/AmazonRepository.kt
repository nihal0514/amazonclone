package com.example.amazonclone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonclone.db.CategoryDB
import com.example.amazonclone.model.stripe.StripeServerModel
import com.example.amazonclone.model.address.AddAddressRequest
import com.example.amazonclone.model.address.AddAddressResponse
import com.example.amazonclone.model.address.AddressItem
import com.example.amazonclone.model.banner.BannerListItem
import com.example.amazonclone.model.cart.CartRequest
import com.example.amazonclone.model.cart.CartResponse
import com.example.amazonclone.model.cart.GetCartResponse
import com.example.amazonclone.model.category.CategoryListItem
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import com.example.amazonclone.model.order.SetOrderResponse
import com.example.amazonclone.model.products.ProdListItem
import com.example.amazonclone.model.products.Product
import com.example.amazonclone.api.ApiHelper
import com.example.amazonclone.utils.UiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AmazonRepository @Inject constructor(
    private val categoryDB: CategoryDB,
    private val apiHelper: ApiHelper
) {
    private val _categoryDeals= MutableLiveData<UiState<List<CategoryListItem>>>()
    val categoryDeals: LiveData<UiState<List<CategoryListItem>>> = _categoryDeals

    private var compositeDisposable = CompositeDisposable()

    fun getCategories(){

       /* val result= apiInterface.getAllCategory()
        Log.d("mm",result.body().toString());
        result.code()
        if(result.isSuccessful){
            result.body().let {
                categoryDB.getCategoryDao().addCategory(it?.categoryList ?: emptyList())
                  _categoryDeals.postValue(it?.categoryList ?: emptyList())

                //for room db
               // _categoryDeals.postValue(categoryDB.getCategoryDao().getCategories())
            }
        }*/
        _categoryDeals.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.getAllCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                 //   categoryDB.getCategoryDao().addCategory(it?.categoryList ?: emptyList())
                    try{
                        _categoryDeals.postValue(UiState.Success(it?.categoryList ?: emptyList()))

                    }catch (e: Exception){
                        _categoryDeals.postValue(UiState.Error(e.toString()))
                    }


                },
        )
    }

    private val _bannerItems= MutableLiveData<UiState<List<BannerListItem>>>()
    val bannerItems: LiveData<UiState<List<BannerListItem>>> = _bannerItems

    fun getBannerImages(){

        /*val result= apiInterface.getAllBanner()
        if(result.isSuccessful){
            result.body().let {
                _bannerItems.postValue(it?.bannerList ?: emptyList())
            }
        }*/
        _bannerItems.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.getAllBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    try{
                        _bannerItems.postValue(UiState.Success(it?.bannerList ?: emptyList()))

                    }catch (e: Exception){
                        _bannerItems.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }

    private val _registerResponse= MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    suspend fun registerUser(registerRequest: RegisterRequest){
        val result= apiHelper.registerUser(registerRequest)
        if(result.isSuccessful){
            result.body().let {
                _registerResponse.postValue(it)
            }
        }
    }


    private val _loginResponse= MutableLiveData<RegisterResponse>()
    val loginResponse: LiveData<RegisterResponse> = _loginResponse

    suspend fun loginUser(loginRequest: LoginRequest){
        val result= apiHelper.loginUser(loginRequest)
        if(result.isSuccessful){
            result.body().let {
                _loginResponse.postValue(it)
            }
        }
    }

    private val _prodListData= MutableLiveData<UiState<List<ProdListItem>>>()
    val prodListData: LiveData<UiState<List<ProdListItem>>> = _prodListData

    fun getproducts(){
       /* val result= apiInterface.getProducts()
        if(result.isSuccessful){
            result.body().let {
                _prodListData.postValue(it?.prodList ?: emptyList())
            }
        }*/
        compositeDisposable.add(
            apiHelper.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    _prodListData.postValue(UiState.Success(it?.prodList ?: emptyList()))
                },
        )
    }

    private val _prodListByIdData= MutableLiveData<Product>()
    val prodListByIdData: LiveData<Product> = _prodListByIdData

    suspend fun getproductsByID(id: String){
       /* val result= apiInterface.getProductsById(id)
        if(result.isSuccessful){
            result.body().let {
                _prodListByIdData.postValue(it?.product !!)
            }
        }*/
        compositeDisposable.add(
            apiHelper.getProductsById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    _prodListByIdData.postValue((it?.product !!))
                },
        )
    }

    private val _addtoCartData= MutableLiveData<UiState<CartResponse>>()
    val addtoCartData: LiveData<UiState<CartResponse>> = _addtoCartData

    fun addToCart(cartRequest: CartRequest,token:String){
      /*  val result= apiInterface.addtoCart(cartRequest,token)

        if(result.isSuccessful){
            result.body().let {
                _addtoCartData.postValue(it)
            }
        }*/
        _addtoCartData.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.addtoCart(cartRequest,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{throwable -> UiState.Error(throwable.message.toString()) }
                .subscribe {
                    try {
                        _addtoCartData.postValue(UiState.Success(it))
                    } catch (e: Exception) {
                        _addtoCartData.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }

    private val _getCartData= MutableLiveData<UiState<GetCartResponse>>()
    val getCartData: LiveData<UiState<GetCartResponse>> = _getCartData

/*
    suspend fun getCartData(token: String){
        val result= apiInterface.getCart(token);
        if(result.isSuccessful){
            result.body().let {
                _getCartData.postValue(it)
            }
        }
    }
*/

    fun getCartData(token: String){
        _getCartData.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.getCart(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{throwable -> UiState.Error(throwable.message.toString()) }
                .subscribe{

                    try{
                        _getCartData.postValue(UiState.Success(it))
                    }catch (e: Exception){
                        _getCartData.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }

    private val _getAddress= MutableLiveData<UiState<List<AddressItem>>>()
    val getAddress: LiveData<UiState<List<AddressItem>>> = _getAddress

    fun getAddress(token: String){
        _getAddress.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.getAddresses(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    try{
                        _getAddress.postValue(UiState.Success(it.addresses?.address ?: emptyList()))
                    }catch (e: Exception){
                        _getAddress.postValue(UiState.Error(e.toString()))
                    }


                }
        )
    }

    private val _addAdress= MutableLiveData<UiState<AddAddressResponse>>()
    val addAddress: LiveData<UiState<AddAddressResponse>> = _addAdress

    fun addAddress(addAddressRequest: AddAddressRequest,token:String){
        _addAdress.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.addNewAddress(addAddressRequest,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    try{
                        _addAdress.postValue(UiState.Success(it))
                    }catch (e: Exception){
                        _addAdress.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }

    private val _getPaymentData= MutableLiveData<UiState<StripeServerModel>>()
    val getPaymentData: LiveData<UiState<StripeServerModel>> = _getPaymentData


    fun getServer(token:String){
        _getPaymentData.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.doOrderPayment(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    try{
                        _getPaymentData.postValue(UiState.Success(it))
                    }catch (e: Exception){
                        _getPaymentData.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }

    private val _setOrderData= MutableLiveData<UiState<SetOrderResponse>>()
    val setOrderData: LiveData<UiState<SetOrderResponse>> = _setOrderData

    fun setOrder(token: String){
        _setOrderData.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.setOrder(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    try{
                        _setOrderData.postValue(UiState.Success(it))
                    }catch (e: Exception){
                        _setOrderData.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }

    private val _deleteCartData= MutableLiveData<UiState<SetOrderResponse>>()
    val deleteCartData: LiveData<UiState<SetOrderResponse>> = _deleteCartData

    fun deleteCart(token: String){
        _deleteCartData.postValue(UiState.Loading)
        compositeDisposable.add(
            apiHelper.deleteCart(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    try{
                        _deleteCartData.postValue(UiState.Success(it))
                    }catch (e: Exception){
                        _deleteCartData.postValue(UiState.Error(e.toString()))
                    }

                },
        )
    }
}