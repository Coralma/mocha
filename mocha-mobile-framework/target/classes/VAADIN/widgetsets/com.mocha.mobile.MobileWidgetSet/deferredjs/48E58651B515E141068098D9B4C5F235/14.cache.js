function vM(){}
function qM(){}
function q7(){}
function B5(){}
function kzb(){}
function jzb(){}
function Hac(){}
function Xac(){}
function _ac(){}
function dbc(){}
function hbc(){}
function abc(b){this.b=b}
function Yac(b){this.b=b}
function ebc(b){bb();this.b=b}
function Vac(b,c){b.enctype=c;b.encoding=c}
function jFb(b,c){b.onload=function(){c.fg()}}
function Jac(b){UGb(b.n,false);b.o||(b.e.Mb[wwc]=true,undefined);b.d=false}
function Kac(b){UGb(b.n,true);b.e.Mb[wwc]=false;b.d=true;if(b.o){Iac(b);b.o=false}}
function Iac(b){if(b.p){$doc.body.removeChild(b.p);b.p.onload=null;b.p=null}}
function r7(){var b;this.Mb=(b=$doc.createElement($uc),b.type=Jwc,b)}
function ibc(b){var c;this.b=b;this.Mb=(c=$doc.createElement($uc),c.type='file',c);this.Mb[mwc]='gwt-FileUpload'}
function Nac(b,c){if(b.f!=c){b.f=c;if(b.f){L1(b.e,1024);L1(b.e,2048)}}v1(b.Mb,'v-upload-immediate',b.f)}
function Mac(b){l2(b.k,b.n);l2(b.k,b.e);b.e=new ibc(b);b.e.Mb.name=b.j+AHc;b.e.Mb[wwc]=!b.d;m6(b.k,b.e);m6(b.k,b.n);b.f&&L1(b.e,1024)}
function xM(){tM=new vM;Vb((Tb(),Sb),14);!!$stats&&$stats(yc(zHc,Ouc,-1,-1));tM.Nc();!!$stats&&$stats(yc(zHc,NCc,-1,-1))}
function uM(){var b,c,d;while(rM){d=nb;rM=rM.b;!rM&&(sM=null);if(!d){(Xxb(),Wxb).Fg(zG,new kzb);ypb()}else{try{(Xxb(),Wxb).Fg(zG,new kzb);ypb()}catch(b){b=XJ(b);if(zr(b,38)){c=b;rvb.Qe(c)}else throw b}}}}
function Oac(b){if(b.e.Mb.value.length==0||b.o||!b.d){rvb.Se('Submit cancelled (disabled, no file or already submitted)');return}Umb(b.b);b.c.submit();b.o=true;rvb.Se('Submitted form');Jac(b);b.q=new ebc(b);db(b.q,800)}
function Lac(b){var c;if(!b.p){c=$doc.createElement(Fwc);c.innerHTML="<iframe src=\"javascript:''\" name='"+b.j+"_TGT_FRAME' style='position:absolute;width:0;height:0;border:0'>"||Juc;b.p=ke(c);$doc.body.appendChild(b.p);b.c.target=b.j+'_TGT_FRAME';jFb(b.p,b)}}
function Pac(){this.Mb=$doc.createElement('form');this.e=new ibc(this);this.k=new p6;this.g=new r7;this.c=this.Mb;Vac(this.Mb,BHc);this.c.method='post';y4(this,this.k);m6(this.k,this.g);m6(this.k,this.e);this.n=new XGb;C1(this.n,new Yac(this),(Ek(),Ek(),Dk));m6(this.k,this.n);this.Mb[mwc]='v-upload';this.Jb==-1?j_(this.Mb,241|(this.Mb.__eventBits||0)):(this.Jb|=241)}
var AHc='_file',CHc='buttoncaption',zHc='runCallbacks14';_=vM.prototype=qM.prototype=new K;_.gC=function wM(){return Au};_.Nc=function AM(){uM()};_.cM={};_=B5.prototype=new _0;_.gC=function C5(){return Ax};_.Sc=function D5(b){G1(this,b)};_.cM={10:1,13:1,15:1,22:1,70:1,71:1};_=r7.prototype=q7.prototype=new _0;_.gC=function s7(){return Px};_.cM={10:1,13:1,15:1,22:1,70:1,71:1};_=kzb.prototype=jzb.prototype=new K;_.bf=function lzb(){return new Pac};_.gC=function mzb(){return nB};_.cM={142:1};_=Pac.prototype=Hac.prototype=new v4;_.gC=function Qac(){return zG};_.jd=function Rac(){F1(this);!!this.b&&Lac(this)};_.Sc=function Sac(b){(j0(b.type)&241)>0&&($wb(this.b.J,b,this,null),undefined);G1(this,b)};_.kd=function Tac(){H1(this);this.o||Iac(this)};_.fg=function Uac(){Rwb((Hc(),Gc),new abc(this))};_.ne=function Wac(b,c){var d;if(anb(c,this,b,true)){return}if('notStarted' in b[1]){db(this.q,400);return}if('forceSubmit' in b[1]){Oac(this);return}Nac(this,Boolean(b[1][Fxc]));this.b=c;this.j=b[1][Dwc];this.i=b[1]['nextid'];d=Zmb(c,b[1][Ixc][lBc]);this.c.action=d;if(CHc in b[1]){this.n.c.textContent=b[1][CHc]||Juc;this.n.Mb.style.display=Juc}else{this.n.Mb.style.display=kvc}this.e.Mb.name=this.j+AHc;if(wwc in b[1]||Dxc in b[1]){Jac(this)}else if(!Boolean(b[1][Xxc])){Kac(this);Lac(this)}};_.cM={10:1,13:1,15:1,17:1,19:1,20:1,21:1,22:1,26:1,34:1,70:1,71:1,76:1,77:1};_.b=null;_.c=null;_.d=true;_.f=false;_.i=0;_.j=null;_.n=null;_.o=false;_.p=null;_.q=null;_=Yac.prototype=Xac.prototype=new K;_.gC=function Zac(){return vG};_.ic=function $ac(b){this.b.f?(this.b.e.Mb.click(),undefined):Oac(this.b)};_.cM={12:1,40:1};_.b=null;_=abc.prototype=_ac.prototype=new K;_.Wb=function bbc(){if(this.b.o){if(this.b.b){!!this.b.q&&cb(this.b.q);rvb.Se('VUpload:Submit complete');Umb(this.b.b)}Mac(this.b);this.b.o=false;Kac(this.b);this.b.Ib||Iac(this.b)}};_.gC=function cbc(){return wG};_.cM={3:1,14:1};_.b=null;_=ebc.prototype=dbc.prototype=new $;_.gC=function fbc(){return xG};_.Sb=function gbc(){rvb.Se('Visiting server to see if upload started event changed UI.');smb(this.b.b,this.b.j,'pollForStart',Juc+this.b.i,true,105)};_.cM={66:1};_.b=null;_=ibc.prototype=hbc.prototype=new B5;_.gC=function jbc(){return yG};_.Sc=function kbc(b){G1(this,b);if(j0(b.type)==1024){this.b.f&&this.b.e.Mb.value!=null&&!lkc(Juc,this.b.e.Mb.value)&&Oac(this.b)}else if((Jqb(),!Iqb&&(Iqb=new brb),Jqb(),Iqb).b.i&&j0(b.type)==2048){this.b.e.Mb.click();this.b.e.Mb.blur()}};_.cM={10:1,13:1,15:1,22:1,70:1,71:1};_.b=null;var Au=Yic(vCc,'AsyncLoader14'),Ax=Yic(yCc,'FileUpload'),Px=Yic(yCc,'Hidden'),nB=Yic(GCc,'WidgetMapImpl$24$1'),vG=Yic(FCc,'VUpload$1'),wG=Yic(FCc,'VUpload$2'),xG=Yic(FCc,'VUpload$3'),yG=Yic(FCc,'VUpload$MyFileUpload');Fuc(xM)();