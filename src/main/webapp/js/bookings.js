$(function () {
    const user_id = selfId;
    const roomIdFromFront = roomIdFromFrontUser;

    var dataTable =  $('#users-list').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "columns": [
            { "width": "350px" },
            { "width": "165px" },
            { "width": "145px" },
            { "width": "115px" },
            { "width": "65px" },
            { "orderable": false, "width": "155px" },
        ],
        "info": true,
        "autoWidth": false,
    });

    $('.click-dismiss-edit-modal').on('click', function() {
        $('#modal-edit').find('.hiden-selected-user').val('');
    });

    $('.click-dismiss-approve-modal').on('click', function() {
        $('#modal-approve').find('.hiden-selected-user').val('');
    });

    $(document).on('click', '.open-edit-modal', function() {
        var edit_data_id = $(this).attr('data-id');
        $('#modal-edit').find('.hidden-selected-user').val('');
        $('#modal-edit').find('.hidden-selected-user').val(edit_data_id);
        $('#appointement-calendar-place').datepicker('update');
        $('#modal-edit #wizzard-append-filtered-dates').html('');
        $('#modal-edit').modal();
    });

    $(document).on('click', '.open-approve-modal', function() {
        var approve_data_id = $(this).attr('data-id');
        $('#modal-approve').find('.hidden-selected-user').val('');
        $('#modal-approve').find('.hidden-selected-user').val(approve_data_id);
        $('#appointement-calendar-place').datepicker('update');
        $('#modal-edit #wizzard-append-filtered-dates').html('');
        $('#modal-approve').modal();
    });

    let dates = [];
    const timesArray = [
        '9:00',
        '10:00',
        '11:00',
        '12:00',
        '13:00',
        '14:00',
        '15:00',
        '16:00',
        '17:00',
        '18:00'
    ];

    $('#appointement-calendar-place').datepicker({
        maxViewMode: 0,
        multidate: true,
        format: 'yyyy-mm-dd',
        startDate: new Date()
    });

    $(document).on('change', 'select.select-first-range-change', function (e) {
        e.preventDefault();

        var selectedEl = $(this);

        selectedEl.parents('.nav-item').find('select.select-second-range-change option').prop('disabled', false);

        selectedEl.parents('.nav-item').find('select.select-second-range-change option').each(function(el) {
            if ($(this).val() != selectedEl.val()) {
                $(this).prop('disabled', true);
            } else if ($(this).val() == selectedEl.val()) {
                return false;
            }
        });
    });

    $('#appointement-calendar-place').on('changeDate', function() {
        $('#appointement-invitation #wizzard-append-filtered-dates').html('');

        const filteredDates = $(this).datepicker('getDates').map(e => {
            const currentDate = new Date(e);
            let month = currentDate.getMonth();
           /* if (month === 0) {
                month = 1
            }*/

            month = month + 1;

            return currentDate.getDate() + '-' + month + '-' + currentDate.getFullYear();
        });

        dates = filteredDates;
        let appendElement = '';
        filteredDates.forEach((element, i) => {
            appendElement += '<li class="nav-item">' +
                '<div class="row">' +
                '<div class="col-md-4">' +
                '<span class="filtered-dates__date">' + element + '</span>' +
                '<input type="hidden" name="selectedDate[' + i + ']" value="' + element + '" />' +
                '</div>'+
                '<div class="time-range-from col-md-4">' +
                '<label class="">From</label>' +
                '<select name="selectedTimeFrom[' + i + ']" class="selectpicker select-first-range-change filtered-dates__selecttime custom-selectpick custom-rounded-dropdown mr-0" required>' +
                '<option value="">Select Time</option>' +
                '<option value="07:00">07:00</option>' +
                '<option value="08:00">08:00</option>' +
                '<option value="09:00">09:00</option>' +
                '<option value="10:00">10:00</option>' +
                '<option value="11:00">11:00</option>' +
                '<option value="12:00">12:00</option>' +
                '<option value="13:00">13:00</option>' +
                '<option value="14:00">14:00</option>' +
                '<option value="15:00">15:00</option>' +
                '<option value="16:00">16:00</option>' +
                '<option value="17:00">17:00</option>' +
                '</select>' +
                '</div>' +
                '<div class="time-range-from col-md-4">' +
                '<label class="">To</label>' +
                '<select name="selectedTimeTo[' + i + ']" class="selectpicker select-second-range-change filtered-dates__selecttime custom-selectpick custom-rounded-dropdown mr-0" required>' +
                '<option value="">Select Time</option>' +
                '<option disabled value="07:00">07:00</option>' +
                '<option disabled value="08:00">08:00</option>' +
                '<option disabled value="09:00">09:00</option>' +
                '<option disabled value="10:00">10:00</option>' +
                '<option disabled value="11:00">11:00</option>' +
                '<option disabled value="12:00">12:00</option>' +
                '<option disabled value="13:00">13:00</option>' +
                '<option disabled value="14:00">14:00</option>' +
                '<option disabled value="15:00">15:00</option>' +
                '<option disabled value="16:00">16:00</option>' +
                '<option disabled value="17:00">17:00</option>' +
                '</select>' +
                '</div>' +
                '</div>' +
                '</li>';
        });

        $('#modal-edit #wizzard-append-filtered-dates').html(appendElement);
    });

    $(document).on('submit', '#book-request-form', function(e) {
        e.preventDefault();

        const formData = $('#book-request-form').serializeArray();
        const data_id = $('#book-request-form').find('.hidden-selected-user').val();

        const _data = {
            data_id: data_id,
            dates: formData
        };

        $.ajax({
            url: manage_book_request_ajax,
            type: 'POST',
            data: _data,
            dataType: 'text',
            beforeSend: function () {
                $('#modal-edit').find('.modal-loader-place').html('<div class="lds-ring"><div></div><div></div><div></div><div></div></div>');
            },
            success: function (data) {
                const newData = JSON.parse(data);

                $('#modal-edit').find('.modal-loader-place').html('');
                if (newData.status == 'success') {

                    console.log('success send message', newData);

                    socket.emit('send_message', {
                        user_id: user_id,
                        sessionRoom: newData.session_token,
                        message: newData.message,
                        messageType: 2,
                        from: user_id,
                        to: newData.user_id
                    }, (error) => {
                        if (error) {
                            console.log('some error', error);
                        }
                    });

                    location.reload();

                } else {

                }
            },
            error: function (data) {
                $('#modal-edit').find('.modal-loader-place').html('');
            }
        });

    });

    $(document).on('submit', '#book-approval-form', function(e) {
        e.preventDefault();

        const data_id = $('#book-approval-form').find('.hidden-selected-user').val();

        const _data = {
            data_id: data_id
        };

        console.log(_data);

        $.ajax({
            url: confirm_book_request_ajax,
            type: 'POST',
            data: _data,
            dataType: 'text',
            beforeSend: function () {
                $('#modal-edit').find('.modal-loader-place').html('<div class="lds-ring"><div></div><div></div><div></div><div></div></div>');
            },
            success: function (data) {
                const newData = JSON.parse(data);

                $('#modal-edit').find('.modal-loader-place').html('');
                if (newData.status == 'success') {
                    console.log('success send message', {
                        user_id: user_id,
                        sessionRoom: newData.session_token,
                        message: newData.message,
                        messageType: 2,
                        from: user_id,
                        to: newData.user_id
                    });
                    socket.emit('send_message', {
                        user_id: user_id,
                        sessionRoom: newData.session_token,
                        message: newData.message,
                        messageType: 2,
                        from: user_id,
                        to: newData.user_id
                    }, (error) => {
                        if (error) {
                            console.log('some error', error);
                        }
                    });

                    $('#modal-approve').modal('hide');

                    location.reload();

                } else {
                    console.log('some error else case');
                }
            },
            error: function (data) {
                $('#modal-edit').find('.modal-loader-place').html('');
            }
        });

    });
});
