package base.security;

import base.service.HistoryAccessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import java.security.KeyFactory;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class BouncyCastleUtils {
	static
	{
		Security.addProvider(new BouncyCastleProvider());
	}
	public static boolean isPublicKeyValid(String publicKey, HistoryAccessService historyAccessService) {
		try {
			publicKey = publicKey
					.replaceAll("\\n", "")
					.replace("-----BEGIN RSA PUBLIC KEY-----", "")
					.replace("-----BEGIN PUBLIC KEY-----", "")
					.replace("-----END RSA PUBLIC KEY-----", "")
					.replace("-----END PUBLIC KEY-----", "");

			byte[] publicKeyBytes = Base64.decode(publicKey);// Convert the string to bytes if it is not in byte format already
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			keyFactory.generatePublic(keySpec);
			return true; // Public key parsing succeeded, it's in the correct format
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), "isPublicKeyValid");
			return false;
			//Public key parsing failed, not in the correct format
		}
	}

//	public static void main(String[] args) {
//		String publicKeyAsString = "-----BEGIN PUBLIC KEY-----\n" + "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXtPo/tToEtyrHbW4MxpVBuSLF\n" + "1J2g9V6obQ9GmHrs+P27/qKO2axn37D6eBhuKpXR7aHRymRxYzuiATdUnZHkPrak\n" + "XY2MAALzK2K/fPyKQVFnfkLfaTSw2gKmXsnng8+xKmYUiZ97e6+GTgpya+C4omSt\n" + "PaOkyvWykCekFeNzhQIDAQAB\n" + "-----END PUBLIC KEY-----";
//
//		System.out.println(isPublicKeyValid(publicKeyAsString));
//	}
}
