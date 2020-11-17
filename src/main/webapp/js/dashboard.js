$(function () {
    'use strict';

    var ticksStyle = {
        fontColor: '#495057',
        fontStyle: 'bold'
    };
    
    var mode      = 'index';
    var intersect = true;

    var $usersChart = $('#users-chart');
    var $usersChart  = new Chart($usersChart, {
        type   : 'bar',
        data   : {

            labels  : users_days_data,
            datasets: [
                {
                    backgroundColor: '#17a2b8',
                    borderColor    : '#17a2b8',
                    data           : employers_quantity_data
                },
                {
                    backgroundColor: '#ffc106',
                    borderColor    : '#ffc106',
                    data           : employees_quantity_data
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
                  return value
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

    $('#select-company').on('change', salesFilter);

    /**
     * filter for sales
     */
    function salesFilter() {
        var company = $('#select-company').val();
        var dateFrom = $('#dateFrom').val();
        var dateTo = $('#dateTo').val();

        var _data = {
            company: company,
            dateFrom: dateFrom,
            dateTo: dateTo
        };
        
        $.ajax({
            url: requestUrl,
            type: 'POST',
            data: _data,
            dataType: 'text',
            beforeSend: function () {
                $('.loading-place').html('');
                $('.loading-place').html('<div class="request-ring"><div></div><div></div><div></div><div></div></div>');
            },
            success: function (data) {
                console.log('data', data);
                var newData ="";
                newData = $.parseJSON(data);
                console.log(newData);
                console.log('newData.allUSers', newData.allUSers);
                $('.company-total-place').html(newData.allCompanies);
                $('.company-pending-place').html(newData.pendingCompanies);
                $('.company-active-place').html(newData.activeCompanies);
                $('.company-inactive-place').html(newData.inActiveCompanies);
                $('.user-total-place').html(newData.allUSers);
                $('.user-pending-place').html(newData.pending);
                $('.user-requests-place').html(newData.Requests);
                $('.user-ratings-place').html(newData.FeedBacks);
                $('.user-active-place').html(newData.active);
                $('.user-inactive-place').html(newData.inActive);
                $('.loading-place').html('');
            },
            error: function (data) {

            }
        });
    }

});