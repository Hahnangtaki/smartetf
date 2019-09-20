import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
import { getEntities as getEtfProducts } from 'app/entities/etf-product-my-suffix/etf-product-my-suffix.reducer';
import { IDealerParticipantMySuffix } from 'app/shared/model/dealer-participant-my-suffix.model';
import { getEntities as getDealerParticipants } from 'app/entities/dealer-participant-my-suffix/dealer-participant-my-suffix.reducer';
import { IEtfExecutionDtlMySuffix } from 'app/shared/model/etf-execution-dtl-my-suffix.model';
import { getEntities as getEtfExecutionDtls } from 'app/entities/etf-execution-dtl-my-suffix/etf-execution-dtl-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './subscript-my-suffix.reducer';
import { ISubscriptMySuffix } from 'app/shared/model/subscript-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISubscriptMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISubscriptMySuffixUpdateState {
  isNew: boolean;
  etfProductId: string;
  dealerParticipantId: string;
  etfExecutionDtlId: string;
}

export class SubscriptMySuffixUpdate extends React.Component<ISubscriptMySuffixUpdateProps, ISubscriptMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      etfProductId: '0',
      dealerParticipantId: '0',
      etfExecutionDtlId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEtfProducts();
    this.props.getDealerParticipants();
    this.props.getEtfExecutionDtls();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { subscriptEntity } = this.props;
      const entity = {
        ...subscriptEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/subscript-my-suffix');
  };

  render() {
    const { subscriptEntity, etfProducts, dealerParticipants, etfExecutionDtls, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.subscript.home.createOrEditLabel">Create or edit a Subscript</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : subscriptEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="subscript-my-suffix-id">ID</Label>
                    <AvInput id="subscript-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="subscriptCodeLabel" for="subscript-my-suffix-subscriptCode">
                    Subscript Code
                  </Label>
                  <AvField
                    id="subscript-my-suffix-subscriptCode"
                    type="text"
                    name="subscriptCode"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="subscriptDateLabel" for="subscript-my-suffix-subscriptDate">
                    Subscript Date
                  </Label>
                  <AvField id="subscript-my-suffix-subscriptDate" type="date" className="form-control" name="subscriptDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="customerIdLabel" for="subscript-my-suffix-customerId">
                    Customer Id
                  </Label>
                  <AvField
                    id="subscript-my-suffix-customerId"
                    type="text"
                    name="customerId"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="customerNameLabel" for="subscript-my-suffix-customerName">
                    Customer Name
                  </Label>
                  <AvField
                    id="subscript-my-suffix-customerName"
                    type="text"
                    name="customerName"
                    validate={{
                      maxLength: { value: 200, errorMessage: 'This field cannot be longer than 200 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="unitBuyPriceIndLabel" for="subscript-my-suffix-unitBuyPriceInd">
                    Unit Buy Price Ind
                  </Label>
                  <AvField id="subscript-my-suffix-unitBuyPriceInd" type="string" className="form-control" name="unitBuyPriceInd" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitBuyPriceLabel" for="subscript-my-suffix-unitBuyPrice">
                    Unit Buy Price
                  </Label>
                  <AvField id="subscript-my-suffix-unitBuyPrice" type="string" className="form-control" name="unitBuyPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitBuyUnitLabel" for="subscript-my-suffix-unitBuyUnit">
                    Unit Buy Unit
                  </Label>
                  <AvField id="subscript-my-suffix-unitBuyUnit" type="string" className="form-control" name="unitBuyUnit" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitBuyLotLabel" for="subscript-my-suffix-unitBuyLot">
                    Unit Buy Lot
                  </Label>
                  <AvField id="subscript-my-suffix-unitBuyLot" type="string" className="form-control" name="unitBuyLot" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitBuyBasketLabel" for="subscript-my-suffix-unitBuyBasket">
                    Unit Buy Basket
                  </Label>
                  <AvField id="subscript-my-suffix-unitBuyBasket" type="string" className="form-control" name="unitBuyBasket" />
                </AvGroup>
                <AvGroup>
                  <Label id="marketLabel" for="subscript-my-suffix-market">
                    Market
                  </Label>
                  <AvField id="subscript-my-suffix-market" type="text" name="market" />
                </AvGroup>
                <AvGroup>
                  <Label id="buyGrossAmountLabel" for="subscript-my-suffix-buyGrossAmount">
                    Buy Gross Amount
                  </Label>
                  <AvField id="subscript-my-suffix-buyGrossAmount" type="string" className="form-control" name="buyGrossAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="feeLabel" for="subscript-my-suffix-fee">
                    Fee
                  </Label>
                  <AvField id="subscript-my-suffix-fee" type="string" className="form-control" name="fee" />
                </AvGroup>
                <AvGroup>
                  <Label id="buyNetAmountLabel" for="subscript-my-suffix-buyNetAmount">
                    Buy Net Amount
                  </Label>
                  <AvField id="subscript-my-suffix-buyNetAmount" type="string" className="form-control" name="buyNetAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusCashLabel" for="subscript-my-suffix-statusCash">
                    Status Cash
                  </Label>
                  <AvField id="subscript-my-suffix-statusCash" type="text" name="statusCash" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusEtfLabel" for="subscript-my-suffix-statusEtf">
                    Status Etf
                  </Label>
                  <AvField id="subscript-my-suffix-statusEtf" type="text" name="statusEtf" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="subscript-my-suffix-status">
                    Status
                  </Label>
                  <AvField id="subscript-my-suffix-status" type="text" name="status" />
                </AvGroup>
                <AvGroup>
                  <Label id="channelLabel" for="subscript-my-suffix-channel">
                    Channel
                  </Label>
                  <AvField id="subscript-my-suffix-channel" type="string" className="form-control" name="channel" />
                </AvGroup>
                <AvGroup>
                  <Label for="subscript-my-suffix-etfProduct">Etf Product</Label>
                  <AvInput id="subscript-my-suffix-etfProduct" type="select" className="form-control" name="etfProductId">
                    <option value="" key="0" />
                    {etfProducts
                      ? etfProducts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="subscript-my-suffix-dealerParticipant">Dealer Participant</Label>
                  <AvInput id="subscript-my-suffix-dealerParticipant" type="select" className="form-control" name="dealerParticipantId">
                    <option value="" key="0" />
                    {dealerParticipants
                      ? dealerParticipants.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="subscript-my-suffix-etfExecutionDtl">Etf Execution Dtl</Label>
                  <AvInput id="subscript-my-suffix-etfExecutionDtl" type="select" className="form-control" name="etfExecutionDtlId">
                    <option value="" key="0" />
                    {etfExecutionDtls
                      ? etfExecutionDtls.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/subscript-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfProducts: storeState.etfProduct.entities,
  dealerParticipants: storeState.dealerParticipant.entities,
  etfExecutionDtls: storeState.etfExecutionDtl.entities,
  subscriptEntity: storeState.subscript.entity,
  loading: storeState.subscript.loading,
  updating: storeState.subscript.updating,
  updateSuccess: storeState.subscript.updateSuccess
});

const mapDispatchToProps = {
  getEtfProducts,
  getDealerParticipants,
  getEtfExecutionDtls,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubscriptMySuffixUpdate);
