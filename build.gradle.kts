import io.mateo.cxf.codegen.wsdl2java.Wsdl2Java
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("io.mateo.cxf-codegen") version "1.2.1"
}

group = "com.jamjaws.riv"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // cxf
    implementation("org.apache.cxf:cxf-spring-boot-starter-jaxws:3.6.1")
    implementation("org.apache.cxf:cxf-rt-features-logging:3.6.1")
    cxfCodegen("jakarta.xml.ws:jakarta.xml.ws-api:2.3.3")
    cxfCodegen("jakarta.annotation:jakarta.annotation-api:1.3.5")
    cxfCodegen("ch.qos.logback:logback-classic:1.2.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Wsdl2Java>().configureEach {
    toolOptions {
        markGenerated.set(true)
    }
}

cxfCodegen {
    cxfVersion.set("3.6.1")
}

tasks.register("financialbillingclaim", Wsdl2Java::class) {
    toolOptions {
        wsdl.set(file("/Users/john/jamjaws/financial-billing-claim-server/src/main/resources/schemas/interactions/ProcessClaimSpecificationInteraction/ProcessClaimSpecificationInteraction_1.0_RIVTABP21.wsdl"))
        wsdlLocation.set("schemas/interactions/ProcessClaimSpecificationInteraction/ProcessClaimSpecificationInteraction_1.0_RIVTABP21.wsdl")
    }
}