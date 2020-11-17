$(function () {
    'use strict';

    var ticksStyle = {
        fontColor: '#495057',
        fontStyle: 'bold'
    };
    
    var mode      = 'index';
    var intersect = true;

    var $salesChart = $('#sales-chart');
    var $salesChart  = new Chart($salesChart, {
        type   : 'bar',
        data   : {
          labels  : enrollment_days_data,
          datasets: [
            {
              backgroundColor: '#87c896',
              borderColor    : '#87c896',
              data           : enrollment_price_data
            }
          ]
        },
        options: {
          maintainAspectRatio: false,
          tooltips           : {
            mode     : mode,
            intersect: intersect
          },
          hover              : {
            mode     : mode,
            intersect: intersect
          },
          legend             : {
            display: false
          },
          scales             : {
            yAxes: [{
              // display: false,
              gridLines: {
                display      : true,
                lineWidth    : '4px',
                color        : 'rgba(0, 0, 0, .2)',
                zeroLineColor: 'transparent'
              },
              ticks    : $.extend({
                beginAtZero: true,
    
                // Include a dollar sign in the ticks
                callback: function (value, index, values) {
                  if (value >= 1000) {
                    value /= 1000;
                    value += 'k'
                  }
                  return '£' + value
                }
              }, ticksStyle)
            }],
            xAxes: [{
              display  : true,
              gridLines: {
                display: false
              },
              ticks    : ticksStyle
            }]
          }
        }
    });

    $('#daterange-select').daterangepicker({
        opens: 'left',
        autoUpdateInput: false
    }, function(start, end, label) {
        $('#dateFrom').val(start.format('YYYY-MM-DD'));
        $('#dateTo').val(end.format('YYYY-MM-DD'));
        $('.sel-daterange').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
        salesFilter();
    });
    
    $('#select-category').on('change', salesFilter);
    $('#select-company').on('change', salesFilter);

    /**
     * filter for sales
     */
    function salesFilter() {
        var category = $('#select-category').val();
        var company = $('#select-company').val();
        var dateFrom = $('#dateFrom').val();
        var dateTo = $('#dateTo').val();

        var _data = {
            category: category,
            company: company,
            dateFrom: dateFrom,
            dateTo: dateTo
        };
        
        $.ajax({
            url: show_more_sales_info_ajax,
            type: 'POST',
            data: _data,
            dataType: 'text',
            beforeSend: function () {
                $('.charts-loading-data-place').html('');
                $('.charts-loading-data-place').html('<div class="request-ring"><div></div><div></div><div></div><div></div></div>');
            },
            success: function (data) {
                var newData = JSON.parse(data);
                if (newData.status === "success") {
                    console.log("test");
                    $salesChart.data.labels = newData.dates;
                    $salesChart.data.datasets[0].data = newData.count;
                    $salesChart.update();
                }

                $('.charts-loading-data-place').html('');
            },
            error: function (data) {

            }
        });
    }

});
