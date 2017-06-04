package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangUnitTesting;
import net.masterthought.dlanguage.psi.impl.DLangUnitTestingImpl;
import net.masterthought.dlanguage.stubs.UnitTestingStubImpl;
import net.masterthought.dlanguage.stubs.interfaces.UnitTestingStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 4/8/2017.
 */
public class UnitTestingStubElementType extends DStubElementType<UnitTestingStub, DLangUnitTesting> {
    public UnitTestingStubElementType(@NotNull String debugName) {
        super(debugName);
    }

    @Override
    public DLangUnitTesting createPsi(@NotNull UnitTestingStub stub) {
        return new DLangUnitTestingImpl(stub, this);
    }

    @NotNull
    @Override
    public UnitTestingStub createStub(@NotNull DLangUnitTesting psi, StubElement parentStub) {
        return new UnitTestingStubImpl(parentStub, this);
    }
    @Override
    public void serialize(@NotNull UnitTestingStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        return;//we don't need to doanything
//        final ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
//        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
//        objectOutputStream.writeObject(stub);
//        final byte[] bytes = byteOutputStream.toByteArray();
//        dataStream.writeInt(bytes.length);
//        dataStream.write(bytes);
    }

    @NotNull
    @Override
    public UnitTestingStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new UnitTestingStubImpl(parentStub, this);
//        final ObjectInputStream objectInputStream = new ObjectInputStream(dataStream);
//        try {
//            return (UnitTestingStub) objectInputStream.readObject();
//        } catch (ClassNotFoundException | ClassCastException e) {
//            throw new IOException(e);
//        }
    }

    @Override
    public void indexStub(@NotNull UnitTestingStub stub, @NotNull IndexSink sink) {

    }
}
