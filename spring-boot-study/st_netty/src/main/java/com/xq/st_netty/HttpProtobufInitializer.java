package com.xq.spstudy.netty;

//import com.skspruce.nms.data.collector.KafkaProducerConfiguration;
//import com.skspruce.nms.data.collector.netty.handlers.HttpProtobufHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
//import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by sk-ziconglu on 2017/3/10.
 */
@Component
@Qualifier("httpProtobufInitializer")
public class HttpProtobufInitializer extends ChannelInitializer<SocketChannel> {
    private static final int MAX_CONTENT_LENGTH = 32 * 1024 * 1024;

//    @Autowired
//    private KafkaTemplate<Bytes, Bytes> kafkaTemplate;

//    @Autowired
//    private KafkaProducerConfiguration kafkaProducerConfiguration;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline()
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(MAX_CONTENT_LENGTH))
                .addLast(new HttpProtobufHandler());
    }
}
