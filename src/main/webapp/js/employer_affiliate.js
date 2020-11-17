import $ from "jquery";

export default () => {

    if (typeof clicked_users === 'undefined' || typeof registered_users === 'undefined' || typeof page_views === 'undefined') {
        return false;
    }

    var pieConfig = {
        type: 'bar',
        data: {
            labels: ' ',
            // datasets: [{
            //     label: '',
            //     backgroundColor: [
            //         'rgba(155, 141, 242, 0.8)',
            //         'rgba(100, 41, 142, 0.8)'
            //     ],
            //     data: affiliate_data,
            //     labels: [
            //         'Red',
            //         'Orange'
            //     ]
            // }]
            datasets: [
                {
                    label: affiliate_labels[0],
                    backgroundColor: 'rgba(155, 141, 242, 0.8)',
                    stack: 'Stack 0',
                    data: clicked_users
                },
                {
                    label: affiliate_labels[1],
                    backgroundColor: 'rgba(100, 41, 142, 0.8)',
                    stack: 'Stack 0',
                    data: registered_users
                },
                {
                    label: affiliate_labels[2],
                    backgroundColor: 'rgba(141, 200, 153, 0.8)',
                    stack: 'Stack 1',
                    data: page_views
                }
            ]
        },
        options: {
            responsive: true,
            tooltips: {
                mode: 'index',
                intersect: false
            },
            maintainAspectRatio: false,
            scales: {
                xAxes: [{
                    display: false //this will remove all the x-axis grid lines
                }]
            }
        }
    };

    if (document.getElementById("affiliate-line")) {
        const ctx = document.getElementById("affiliate-pie").getContext("2d");
        window.myPie = new Chart(ctx, pieConfig);
        ctx.height = 350;
    }

    //ajax request
    $(".employer-selector").select2({
        tags: true,
        tokenSeparators: [',', ' ']
    })

    $('.save-links').on('click', function () {
    console.log($('#add-affiliate').val());
        var _data = {
            white_domain: $('#add-affiliate').val()
        };

        /*
        $.ajax({
            url: contact_form_url,
            type: 'POST',
            data: _data,
            dataType: 'text',
            success: function(data){

                var new_data = JSON.parse(data);

                if(new_data.status == 1) {

                } else {

                }
            }
        });*/
    });
}
