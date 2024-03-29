package net.glowstone.net.codec;

import net.glowstone.msg.ProgressBarMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.io.IOException;

public final class ProgressBarCodec extends MessageCodec<ProgressBarMessage> {

	public ProgressBarCodec() {
		super(ProgressBarMessage.class, 0x69);
	}

	@Override
	public ProgressBarMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readUnsignedByte();
		int progressBar = buffer.readUnsignedShort();
		int value = buffer.readUnsignedShort();
		return new ProgressBarMessage(id, progressBar, value);
	}

	@Override
	public ChannelBuffer encode(ProgressBarMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.buffer(5);
		buffer.writeByte(message.getId());
		buffer.writeShort(message.getProgressBar());
		buffer.writeShort(message.getValue());
		return buffer;
	}

}
