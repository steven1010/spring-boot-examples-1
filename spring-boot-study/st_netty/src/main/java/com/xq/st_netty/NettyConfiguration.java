package com.xq.st_netty;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by sk-ziconglu on 2017/3/9.
 */
@Configuration
@ComponentScan("com.xq.spstudy")
public class NettyConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyConfiguration.class);

    @Value("${sks.netty.boss.thread.count:1}")
    private int bossCount;

    @Value("${sks.netty.worker.thread.count:1}")
    private int workerCount;

    @Value("${sks.netty.hostname:127.0.0.1}")
    private String hostname;

    @Value("${sks.netty.port:3636}")
    private int port;

    @Autowired
    @Qualifier(value = "httpProtobufInitializer")
    private HttpProtobufInitializer httpProtobufInitializer;


    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workerGroup());
        serverBootstrap.channel(NioServerSocketChannel.class);
        
        if (LOGGER.isTraceEnabled())
            serverBootstrap.handler(new LoggingHandler(LogLevel.TRACE));
        if (LOGGER.isDebugEnabled())
            serverBootstrap.handler(new LoggingHandler(LogLevel.DEBUG));
        
        serverBootstrap.childHandler(httpProtobufInitializer);
        return serverBootstrap;
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount);
    }

    @Bean(name = "inetSocketAddress")
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(hostname, port);
    }

}