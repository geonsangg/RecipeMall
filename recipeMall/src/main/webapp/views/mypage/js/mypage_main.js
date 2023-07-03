$(function () {
    let truckImg = $(".truck_img");
    let orderPlaceLeft = $(".order_place").position().left;
    let orderHomeLeft = $(".order_home").position().left;

    truckImg.css("left", orderPlaceLeft + 100 + "px");

    function moveTruckImg() {
        let truckImgLeft = truckImg.position().left;
        if (truckImgLeft >= orderHomeLeft-120) {
            return;
        }
        truckImg.css("left", truckImgLeft + 6 + "px");
        setTimeout(moveTruckImg, 20);
    }
    moveTruckImg();
});
