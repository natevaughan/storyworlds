"use strict";

describe('messageService', function() {
    var messageService;

    beforeEach(module('storyworlds.messageService'));

    beforeEach(inject(function(_messageService_) {
        messageService = _messageService_;
    }));

    it('should pass a simple test', function() {
        expect(Array.isArray(messageService.messages)).toBe(true);
    })

    
})