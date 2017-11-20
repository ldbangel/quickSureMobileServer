package com.towerswatson.rto.smf.types;

import java.io.IOException;

import javax.xml.bind.JAXBElement;

import com.towerswatson.rto.dpo.services._2010._01.DpoService;
import com.towerswatson.rto.dpo.services._2010._01.DpoServiceGetPofConfigurationFaultContractFaultFaultMessage;
import com.towerswatson.rto.dpo.services._2010._01.DpoServiceGetPofSevereFaultContractFaultFaultMessage;
import com.towerswatson.rto.dpo.services._2010._01.DpoService_Service;
import com.towerswatson.rto.dpo.services._2010._01.ObjectFactory;
import com.towerswatson.rto.dpo.services._2010._01.PofRequest;
import com.towerswatson.rto.dpo.services._2010._01.PofResponse;

public class radarinforTest {
 
public static void main(String[] args) throws IOException {
//	      String a = "src/radarinforRequest.xml";
//        File file = new File(a);
//        String SoapXml = FileUtils.readFileToString(file, "utf-16");
       // String sendSoapXml =SoapXml.toString();
		String sendSoapXml="<?xml version=\"1.0\" encoding=\"utf-16\"?> <root> <quote> <CoverCombustionLevel value =\"1\"/> <CoverCTPLLevel value =\"1\"/> <CoverDedWaiverCombust value =\"1\"/> <CoverDedWaiverDriverLiab value =\"1\"/> <CoverDedWaiverMOD value =\"1\"/> <CoverDedWaiverPssgrLiab value =\"1\"/> <CoverDedWaiverTheft value =\"1\"/> <CoverDedWaiverVTPL value =\"1\"/> <CoverDriverLiabLevel value =\"20000\"/> <CoverGlassLevel value =\"1\"/> <CoverMODLevel value =\"1\"/> <CoverPassengerLiabLevel value =\"20000\"/> <CoverVehicleTheft value =\"1\"/> <CoverVTPLLevel value =\"50000\"/> <DriverMainAgeAtPolicyStart value =\"30\"/> <DriverMainDOB value =\"1956/06/28 T 00:00:00 +00:00\"/> <DriverMainDrivingExperience value =\"3yr\"/> <DriverMainDrivingLicenceDate value =\"1976/06/28 T 00:00:00 +00:00\"/> <DriverMainDrivingLicenceNumber value =\"0\"/> <DriverMainDrivingLicenceStatus value =\"0\"/> <DriverMainDrivingLicenceType value =\"C1\"/> <DriverMainGender value =\"0\"/> <JYABSFlag value =\"0\"/> <JYAirbagFlag value =\"0\"/> <JYAlarmFlag value =\"0\"/> <JYBrandName value =\"0\"/> <JYDisplacement value =\"2000\"/> <JYFamilyName value =\"0\"/> <JYFullWeight value =\"1500\"/> <JYGearboxType value =\"0\"/> <JYNewEnergyFlag value =\"1\"/> <JYPurchasePrice value =\"100000\"/> <JYSeat value =\"5\"/> <JYVehicleImport value =\"Domestic\"/> <JYVehicleName value =\"0\"/> <JYVehicleTypeUsage value =\"11\"/> <OwnerAgeAtPolicyStart value =\"30\"/> <OwnerDOB value =\"1966/06/28 T 00:00:00 +00:00\"/> <OwnerDrivingExperience value =\"0\"/> <OwnerDrivingLicenceDate value =\"1986/06/28 T 00:00:00 +00:00\"/> <OwnerDrivingLicenceNumber value =\"0\"/> <OwnerDrivingLicenceStatus value =\"0\"/> <OwnerDrivingLicenceType value =\"0\"/> <OwnerGender value =\"Male\"/> <ProposerAgeAtPolicyStart value =\"30\"/> <ProposerDOB value =\"1966/06/28 T 00:00:00 +00:00\"/> <ProposerDrivingExperience value =\"1986/06/28 T 00:00:00 +00:00\"/> <ProposerDrivingLicenceDate value =\"1986/06/28 T 00:00:00 +00:00\"/> <ProposerDrivingLicenceNumber value =\"0\"/> <ProposerDrivingLicenceStatus value =\"0\"/> <ProposerDrivingLicenceType value =\"0\"/> <ProposerGender value =\"0\"/> <PolicyChannel value =\"Agent\"/> <PolicyDurationwithQS value =\"0\"/> <PolicyDurationwithSS value =\"0\"/> <PolicyFirstStartDate value =\"2017/01/28 T 00:00:00 +00:00\"/> <PolicyKnownSS value =\"0\"/> <PolicyLastYearProvince value =\"Jiangsu\"/> <PolicyNumber value =\"12345\"/> <PolicyFirstQuoteDate value =\"2017/01/27 T 00:00:00 +00:00\"/> <PolicyLastQuoteDate value =\"2017/01/27 T 00:00:00 +00:00\"/> <PolicyStartDateComm value =\"2017/01/28 T 00:00:00 +00:00\"/> <PolicyStartDateCTPL value =\"2017/01/28 T 00:00:00 +00:00\"/> <PTMODStandardRiskPremium value =\"200\"/> <PTNoClaimsDiscount value =\"0.85\"/> <PTPolicyEndDateComm value =\"2017/01/27 T 00:00:00 +00:00\"/> <PTPolicyEndDateCTPL value =\"2017/01/27 T 00:00:00 +00:01\"/> <PTTrafficViolationFactor value =\"0.85\"/> <PTCTPLPrem value =\"0\"/> <PTTax value =\"0\"/> <ScreenProcessCurrentStep value =\"0\"/> <ScreenProcessFurthestStep value =\"0\"/> <VehicleAgeatPolicyStartMonths value =\"12\"/> <VehicleAgeAtPolicyStartYrs value =\"0\"/> <VehicleCurrentGlassType value =\"0\"/> <VehicleDateFirstRegistration value =\"2014/01/27 T 00:00:00 +00:00\"/> <VehicleEngineSize value =\"2000\"/> <VehicleLicencePlate value =\"苏D34352\"/> <VehicleLocationCity value =\"0\"/> <VehicleLocationDistrict value =\"0\"/> <VehicleLocationPostalCode value =\"0\"/> <VehicleLocationProvince value =\"Jiangsu\"/> <VehicleNumPreviousOwners value =\"0\"/> <VehicleSecondHand value =\"0\"/> <VehicleSSHighRiskGroup value =\"0\"/> <VehicleVIN value =\"LFKDI333JKJE8IGTH\"/> <SSPremCombustion value =\"100\"/> <SSPremDedWaiverCombust value =\"100\"/> <SSPremDedWaiverDriverLiab value =\"100\"/> <SSPremDedWaiverMOD value =\"100\"/> <SSPremDedWaiverPssgrLiab value =\"100\"/> <SSPremDedWaiverTheft value =\"100\"/> <SSPremDedWaiverVTPL value =\"100\"/> <SSPremDriverLiab value =\"100\"/> <SSPremGlass value =\"100\"/> <SSPremGovtVehicleTax value =\"100\"/> <SSPremMOD value =\"100\"/> <SSPremPassengerLiab value =\"100\"/> <SSPremVehicleTheft value =\"100\"/> <SSPremVTPL value =\"100\"/> <SSPremTotal value =\"100\"/> <SSVehicleDepreciatedValue value=\"3000\"/> <VehicleRegulationZone value=\"guangdong\"/> <PTAllowedSeats value=\"5\"/> </quote> </root> ";
	    PofRequest pofRequest=new PofRequest();
		PofResponse pofResponse=new PofResponse();
		ObjectFactory objfac=new ObjectFactory();
		PofrInformationCollectionDataContract pofrCollectionsCollectionDataContract= new PofrInformationCollectionDataContract();	
		PofrInformationDataContract pofrInformationDataContract=new PofrInformationDataContract();
		pofrInformationDataContract.setPofr(sendSoapXml);
		pofrCollectionsCollectionDataContract.getPofrInformationDataContract().add(pofrInformationDataContract);
		JAXBElement<PofrInformationCollectionDataContract> pofrCollection=objfac.createPofRequestPofrCollection(pofrCollectionsCollectionDataContract);	
		pofrCollection.setValue(pofrCollectionsCollectionDataContract);		
		pofRequest.setPofrCollection(pofrCollection);
		DpoService_Service dpoService_Service=new DpoService_Service();
		try {
			DpoService dpos=dpoService_Service.getDefaultDpoServiceEndpoint();
			pofResponse=dpos.getPof(pofRequest);
			String responXmlString = pofResponse.getPofCollection().getValue().getPofInformationDataContract().get(0).getPof().getValue();
		   System.out.println(responXmlString);
		} catch (DpoServiceGetPofConfigurationFaultContractFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DpoServiceGetPofSevereFaultContractFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}
}
