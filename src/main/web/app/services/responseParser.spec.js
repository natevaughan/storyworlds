"use strict";

describe('response parser', function() {
    var responseParserService;

    beforeEach(module('responseParserService'));

    beforeEach(inject(function(_responseParserService_) {
        responseParserService = _responseParserService_;
    }));


    it('should concat haha', function() {
        expect(responseParserService.parse('foo')).toEqual('foohahaha!');
    })

    it('should add 1', function() {
        expect(responseParserService.add(3)).toEqual(4);
    })
})