$(document).ready(function() {
    var delivery_price = 2500;
    var total_goods_price = 0;
    
    function updateTotalPrice() {
        total_goods_price = 0;
        $('input[name=prodName]:checked').each(function() {
            var product_count = parseInt($(this).closest('tr').find('.count').val());
            var product_price = parseInt($(this).closest('tr').find('.product_sangpum_price').text());
            total_goods_price += product_count * product_price;
        });
        $('#total_goods_price').text(total_goods_price);
        $('#total_price').text(delivery_price + total_goods_price);
        if (total_goods_price === 0) {
            $('#total_price').text(0);
        }
    }
      
    $('#product_chk_all').click(function() {
        $('input[name=prodName]').prop('checked', this.checked);
        updateTotalPrice();
    });

    $('input[name=prodName]').change(function() {
        updateTotalPrice();
    });

    function updateProductChooseCount() {
        var count = $('input[name=prodName]:checked').length;
        $('.product_choose_count').text(count);
    }

    $('#product_chk_all').click(function() {
        $('input[name=prodName]').prop('checked', this.checked);
        updateProductChooseCount();
        updateTotalPrice();
    });

    $('input[name=prodName]').change(function() {
        updateProductChooseCount();
        updateTotalPrice();
    });

    updateTotalPrice(); 
    //수량 - +   
    function minus(){
        let num=parseInt($(this).closest('tr').find('.count').val());
        if(num>1){
            $(this).closest('tr').find('.count').val(num-1);
            updateTotalPrice(); 
        }
    }
    
    function plus(){
        let num=parseInt($(this).closest('tr').find('.count').val());
        $(this).closest('tr').find('.count').val(num+1);
        updateTotalPrice(); 
    }
    
    $('.product_count_minus').on('click',function(){
        minus.call(this);
    });
    
    $('.product_count_plus').on('click',function(){
        plus.call(this);
    });
});

