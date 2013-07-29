function I3(){}
function D3(){}
function xLb(){}
function wLb(){}
function EFc(){}
function KFc(){}
function OFc(){}
function SFc(){}
function WFc(){}
function $Fc(){}
function LFc(b){this.b=b}
function PFc(b){this.b=b}
function TFc(b){this.b=b}
function XFc(b){Vb();this.b=b}
function _Fc(b){this.b=b;Ncb.call(this);this.Qb[v1c]=v1c}
function K3(){G3=new I3;rd((pd(),od),56);!!$stats&&$stats(Wd(s1c,rMc,-1,-1));G3.ld();!!$stats&&$stats(Wd(s1c,KUc,-1,-1))}
function H3(){var b,c,d;while(E3){d=Lc;E3=E3.b;!E3&&(F3=null);if(!d){(uGb(),tGb).Qg(pM,new xLb);Kxb()}else{try{(uGb(),tGb).Qg(pM,new xLb);Kxb()}catch(b){b=SN(b);if(qt(b,37)){c=b;QDb.Ye(c)}else throw b}}}}
function FFc(b){var c,d;if(b.c.c!=0){d=pt(Azc(b.c,0));QDb.$e('Posting file '+d.name+' to '+b.j);c=Nkb();Mkb(c,b.i);c.open(jNc,b.j,true);c.setRequestHeader('Accept','text/html,vaadin/filexhr');c.setRequestHeader(lNc,VWc);c.send(d);Xb(new XFc(b),700)}}
function GFc(b){var c,d,e,f,g,i,k;e=b.d.Qb.files.length;d=et(KN,{30:1,35:1,60:1},1,e,0);for(f=0;f<e;++f){c=b.d.Qb.files[f];uzc(b.c,c);d[f]=(i=c.size?c.size:0,g=c.name,k=c.type,i+t1c+g+t1c+k+t1c)}lvb(b.b,b.f,'filequeue',d,true);$tb(b.k,false);b.d.Qb[PNc]=true}
function HFc(){this.Qb=$doc.createElement(YNc);this.d=new _Fc(this);this.g=new Bdb;this.e=new Deb;this.i=new LFc(this);this.c=new Fzc;Jbb(this,this.g);ydb(this.g,this.e);ydb(this.g,this.d);this.k=new cub;rb(this.k,new TFc(this),(mm(),mm(),lm));ydb(this.g,this.k);this.Qb[_Lc]=WWc;Ab(this.d,1024);Ab(this.d,2048);kb(this.Qb,UWc,true)}
var t1c='---xXx---',v1c='multiple',u1c='ready',s1c='runCallbacks56';_=I3.prototype=D3.prototype=new M;_.gC=function J3(){return _y};_.ld=function N3(){H3()};_.cM={};_=xLb.prototype=wLb.prototype=new M;_.kf=function yLb(){return new HFc};_.gC=function zLb(){return TE};_.cM={147:1};_=HFc.prototype=EFc.prototype=new Gbb;_.gC=function IFc(){return pM};_.ic=function JFc(b,c){if(gvb(c,this,b,true)){return}kb(this.Qb,UWc,true);this.b=c;this.f=b[1][WNc];this.j=dvb(c,b[1][cPc][j$c]);aub(this.k,b[1][XWc]);this.d.Qb.name=this.f+TWc;PNc in b[1]||YOc in b[1]?($tb(this.k,false),this.d.Qb[PNc]=true,undefined):Boolean(b[1][oPc])||($tb(this.k,true),this.d.Qb[PNc]=false,undefined);if(u1c in b[1]){QDb.$e('The server knows about coming files. Start posting files');FFc(this)}};_.cM={10:1,13:1,15:1,17:1,19:1,20:1,21:1,22:1,26:1,33:1,72:1,73:1,78:1,79:1};_.b=null;_.f=null;_.j=null;_.k=null;_=LFc.prototype=KFc.prototype=new M;_.gC=function MFc(){return lM};_.zc=function NFc(b){if(b.readyState==4){Lkb(b);QDb.$e('Ready state + '+b.readyState);oFb((de(),ce),new PFc(this))}};_.cM={};_.b=null;_=PFc.prototype=OFc.prototype=new M;_.tc=function QFc(){if(this.b.b.Mb&&this.b.b.c.c!=0){xub(this.b.b.b,this.b.b.f,u1c,vOc,true,98);FFc(this.b.b)}};_.gC=function RFc(){return kM};_.cM={3:1};_.b=null;_=TFc.prototype=SFc.prototype=new M;_.gC=function UFc(){return mM};_.Ic=function VFc(b){this.b.d.Qb.click()};_.cM={12:1,39:1};_.b=null;_=XFc.prototype=WFc.prototype=new Tb;_.gC=function YFc(){return nM};_.kc=function ZFc(){$ub(this.b.b)};_.cM={68:1};_.b=null;_=_Fc.prototype=$Fc.prototype=new Mcb;_.gC=function aGc(){return oM};_.dc=function bGc(b){vb(this,b);if(r8(b.type)==1024){this.Qb.files.length>0&&GFc(this.b)}else if(r8(b.type)==2048){this.b.d.Qb.click();this.b.d.Qb.blur()}};_.cM={10:1,13:1,15:1,22:1,72:1,73:1};_.b=null;var _y=luc(kUc,'AsyncLoader56'),TE=luc(uUc,'WidgetMapImpl$70$1'),lM=luc(GUc,'VMultiUpload$1'),kM=luc(GUc,'VMultiUpload$1$1'),mM=luc(GUc,'VMultiUpload$2'),nM=luc(GUc,'VMultiUpload$3'),oM=luc(GUc,'VMultiUpload$MyFileUpload');YLc(K3)();