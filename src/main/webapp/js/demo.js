(function ($) {
  'use strict';

    $('.toastrDefaultSuccess').click(function() {
        toastr.success('Lorem ipsum dolor sit amet, consetetur sadipscing elitr.')
    });
    $('.toastrDefaultError').click(function() {
        toastr.error('Lorem ipsum dolor sit amet, consetetur sadipscing elitr.')
    });

    /**
     * Detect message type for file
     */
    $('.send-message-form input[type=radio][name=messageType]').change(function() {
        if (this.value == 'message') {
            $('.select-file-place-chat').show();
        } else if (this.value == 'notification') {
            $('.select-file-place-chat').hide();
        }
    });
      
})(jQuery);