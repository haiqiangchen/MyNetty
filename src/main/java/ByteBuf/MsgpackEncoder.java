package ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @author haiqiang
 * @date 2018/12/5 9:15
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack=new MessagePack();
        byte[] raw=msgpack.write(o);
        byteBuf.writeBytes(raw);
    }
}
