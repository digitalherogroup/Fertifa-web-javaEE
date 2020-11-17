$(function () {
    'use strict';
    console.log('use strict');
    $('#discount-action').change(function (e) {
        sendAction(e)
    })

    $('#calculate-action').change(function (e) {
        sendAction(e)
    })

    function sendAction(e) {

        const percentageDiscount = $('.discount-block').find('.discount-action').val()
        const monthsDiscount = $('.discount-block').find('.calculate-action').val()
        console.log(percentageDiscount);
        console.log(monthsDiscount);
        const _data = {
            percentageDiscount: percentageDiscount,
            monthsDiscount: monthsDiscount,
        }

        $.ajax({
            url: calculateDiscount,
            type: 'POST',
            data: _data,
            dataType: 'text',
            beforeSend: function () {

            },
            success: function (data) {
                var newData = JSON.parse(data);
                console.log('newData', newData)
                if (newData.status === "success") {

                    $(".discount-final-amount").html(newData.data.totalDiscountAmount);
                    $(".discount-final-months").html(newData.data.totalFeeByMonths);
                }

            },
            error: function (data) {
                console.log('data error', data)
            }
        });
    }
})
