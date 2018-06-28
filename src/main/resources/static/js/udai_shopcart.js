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
                  alert(json);
                 _this.productList=json.data;

            });
        },
        changeMoney:function (product,way) {
            //alert(product.productQuentity);
            if (way>0){
                product.orderamount++;
            }else{
                product.orderamount--;
                if(product.orderamount<1){
                    alert("亲，不能再减啦！");
                    product.orderamount=1;
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
        /*计算购物车中全部商品的总消费*/
        calcTotalPrice: function () {
            var _this = this;
            this.totalMoney = 0;
            this.totalMum=0;
            this.productList.forEach(function (item, index) {
                if (item.checked) {
                    _this.totalMoney += item.price * item.orderamount;
                    _this.totalMum+=item.orderamount;
                }
            });

        },
        delConfrim: function (item) {
            this.delFlag = true;
            this.product = item;
            this.delId=item.id;

        },
        delProduct: function () {
            var _this=this;
            this.$http.get("/shop/delShop",{"id":_this.delId}).then(function (data) {
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

        },


}

})
Vue.filter("money", function (value, type) {
    return "￥" + value.toFixed(2) + type;
})




































