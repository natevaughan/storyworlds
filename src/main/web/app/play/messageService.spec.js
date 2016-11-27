"use strict";

describe('messageService', function() {
    var messageService;

    beforeEach(module('storyworlds.messageService'));

    beforeEach(inject(function(_messageService_) {
        messageService = _messageService_;
    }));

    it('should trim the messages object', function() {
        messageService.push('foo');
        messageService.push('bar');
        expect(messageService.getMessages().length).toEqual(1);
        expect(messageService.getMessages()[0]).toEqual('bar');
    })


    it('should allow messageCount to be updated', function() {
        messageService.setMessageCount(3);
        messageService.push('foo');
        messageService.push('bar');
        messageService.push('baz');
        messageService.push('bing');
        expect(messageService.getMessages().length).toEqual(3);
        expect(messageService.getMessages()[0]).toEqual('bar');
    })
})