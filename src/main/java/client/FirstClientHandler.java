package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author haiqiang
 * @date 2018/12/4 15:11
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date()+": 客户端启动");

        //获取数据
        ByteBuf buf=getByteBuf(ctx);

        //2、写数据
        ctx.channel().writeAndFlush(buf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        //1、获取二进制抽象byteBuf
        ByteBuf buf=ctx.alloc().buffer();

        //2、准备数据
        byte[] bytes="hello world".getBytes(Charset.forName("utf-8"));

       //3、将数据缓存到buf
        buf.writeBytes(bytes);

        return buf;


    }
}
