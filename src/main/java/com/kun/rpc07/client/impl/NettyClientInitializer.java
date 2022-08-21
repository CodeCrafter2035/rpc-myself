package com.kun.rpc07.client.impl;

import com.kun.rpc07.common.RPCRequest;
import com.kun.rpc07.common.RPCResponse;
import com.kun.rpc07.protocol.MessageCodec;
import com.kun.rpc07.protocol.ProcotolFrameDecoder;
import com.kun.rpc07.protocol.decode.MessageDecode;
import com.kun.rpc07.protocol.encode.MessageEncode;
import com.kun.rpc07.serialize.Serializer;
import com.kun.rpc07.serialize.kyro.KryoSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 同样的与服务端解码和编码格式
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        Serializer serializer = new KryoSerializer();
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProcotolFrameDecoder());
        pipeline.addLast(new LoggingHandler());
        pipeline.addLast(new MessageDecode(serializer, RPCResponse.class));
        pipeline.addLast(new MessageEncode(serializer, RPCRequest.class));
        pipeline.addLast(new NettyClientHandler());
    }
}
