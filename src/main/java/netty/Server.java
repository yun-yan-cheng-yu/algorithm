package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {
    public static void main(String[] args) {

        new ServerBootstrap()
                .group(new NioEventLoopGroup(),new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        ChannelPipeline pip = socketChannel.pipeline();
                        pip.addLast(new StringDecoder());
                        pip.addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                                socketChannel.writeAndFlush(msg+"world");
                            }
                        });
                        pip.addLast(new StringEncoder());
//                        pip.addLast(new ChannelOutboundHandlerAdapter(){
//                            @Override
//                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//                                System.out.println("222");
//                                super.write(ctx, msg, promise);
//                            }
//                        });
                    }
                })
                .bind(8080)
        ;
    }
}
