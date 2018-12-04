package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haiqiang
 * @date 2018/12/4 14:40
 */
public class NettyClient {

    private final static String host="127.0.0.1";
    private final static int port=8080;
    //重连接次数
    private final static int retry=3;
    private final static Logger log= LoggerFactory.getLogger(NettyClient.class);

    public static void main(String[] args){
        NioEventLoopGroup workerGroup=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new FirstClientHandler());
                    }
                });

       connect(bootstrap,host,port,retry);
    }
    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
       bootstrap.connect(host,port) .addListener(future -> {
           if (future.isSuccess()){
               log.info("连接成功");
           }else if(retry==0){
               log.error("重连接的次数用光");
           }else {
               log.info("连接失败");
               connect(bootstrap,host,port,retry);
           }
       });
    }
}
