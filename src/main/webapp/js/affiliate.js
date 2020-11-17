$(function () {
    'use strict';

    var ticksStyle = {
        fontColor: '#495057',
        fontStyle: 'bold'
    };

    var mode = 'index';
    var intersect = true;

    var $salesChart = $('#affiliate-pie');
    var $salesChart = new Chart($salesChart, {
        type: 'bar',
        data: {
            labels: ' ',
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
    });
});
