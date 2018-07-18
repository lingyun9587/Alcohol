new Vue({
    el:"#showshopping",
    data:{
        totalMoney:0,
        totalMum:0,
        productList:[],
        checkAllFlag: false,
        delFlag: false,
        delId:0,
        delRessult:0,
    },
    filters:{
      /*  formatMoney: function (value) {
            return "￥" + value.toFixed(2)//前端不建议数据格式转换，会丢失精度
        }*/
    },
    mounted:function () {
        this.$nextTick(function () {
            this.cartView();
        })

    },
    methods:{
        cartView:function () {
            var _this=this;

            this.$http.get("/shop/selshopAll").then(function (json) {

                 _this.productList=json.data;

            });
        },
        changeMoney:function (product,way) {
            var _this=this;
            //alert(product.productQuentity);
            if (way>0){-
                product.num++;
                this.$http.get("/shop/numPlusOrReduce",{"skuId":product.sku.skuId,"num":product.num}).then(function(json){

                });
            }else{
                product.num--;
                if(product.num>0){
                    this.$http.get("/shop/numPlusOrReduce",{"skuId":product.sku.skuId,"num":product.num}).then(function(json){

                    });
                }

                if(product.num<1){
                    alert("亲，不能再减啦！");
                    product.num=1;
                }
            }
            this.calcTotalPrice();
       },
        /*选种商品*/
        selectedProduct: function (item) {
            if (typeof item.checked == "undefined") {
                //Vue.set(item,"checked",true);//全局注册
                this.$set(item, "checked", true);//局部注册
            } else {
                item.checked = !item.checked;
            }
            this.calcTotalPrice();

        },
        checkAll: function (flag) {
            this.checkAllFlag = flag;
            var _this = this;
            this.productList.forEach(function (item, index) {
                if (typeof item.checked == "undefined") {
                    _this.$set(item, "checked", _this.checkAllFlag);//局部注册
                } else {
                    item.checked = _this.checkAllFlag;
                }
            });
            this.calcTotalPrice();
        },
        submitOrder: function(){
            var _this = this;
            var productOrders=new Array();
            this.productList.forEach(function (item, index) {
                if (item.checked) {
                    productOrders.push(item.sku.skuId);
                }
            });
            if(productOrders.length!=0){
                this.$http.get("/shop/transferToOrder",{"productorders":JSON.stringify(productOrders)}).then(function(json){
                    if(json.data==0){
                        alert("要登录才能提交订单哦~");
                    }else{
                        location.href="udai_shopcart_pay.html";
                    }

                });

            }else{
                alert("请至少选择一件商品提交订单！");
            }

            /*this.$http.get("/shop/transferToOrder",{""}).then(function (data) {

            });*/
        },
        /*计算购物车中全部商品的总消费*/
        calcTotalPrice: function () {
            var _this = this;
            this.totalMoney = 0;
            this.totalMum=0;
            this.productList.forEach(function (item, index) {
                if (item.checked) {
                    _this.totalMoney += item.sku.presentPrice * item.num;
                    _this.totalMum+=item.num;
                }
            });

        },
        delConfrim: function (item) {
            this.delFlag = true;
            this.product = item;
            this.delId=item.sku.skuId;
        },
        delProduct: function () {
            var _this=this;
            this.$http.get("/shop/delShop",{"skuId":_this.delId}).then(function (data) {
             _this.delRessult=data.data;
            if(_this.delRessult>0){
                 alert("删除成功！");
                 this.cartView();
            }else{
                alert("删除失败！");
            }
            },function () {
                alert("删除失败1！");
            });

            this.delFlag = false;

        }


}

})
Vue.filter("money", function (value, type) {
    return "￥" + value.toFixed(2) + type;
})




































